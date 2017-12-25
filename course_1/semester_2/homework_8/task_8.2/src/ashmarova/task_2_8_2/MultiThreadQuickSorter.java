package ashmarova.task_2_8_2;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contains realization of quick sort with more than one thread.
 * @param <T> is type of data to sort
 */
public class MultiThreadQuickSorter<T> extends QSorter<T> {
    private final int LIMIT_OF_THREADS;
    private ExecutorService pool;
    private final ThreadsCounter counter = new ThreadsCounter();

    /**
     * Constructor, which contains comparator and max amount of threads
     * @param comparator to compare data in array
     * @param maxAmountOfThreads is max amount of threads
     */
    MultiThreadQuickSorter(Comparator<T> comparator, int maxAmountOfThreads) {
        super(comparator);
        this.LIMIT_OF_THREADS = maxAmountOfThreads;
        this.pool = Executors.newFixedThreadPool(maxAmountOfThreads);
    }

    /**
     * counts threads, which are situated in the moment
     */
    private class ThreadsCounter {
        int threads;
    }

    /**
     * sorts array, using quick sort algorithm with some threads.
     * @param array is data to sort
     */
    @Override
    public void sort(T[] array) {
        if (counter.threads > 0) {
            return;
        }

        counter.threads = 1;
        pool.submit(() -> multiThreadQuickSort(array, 0, array.length - 1, true));

        boolean process = true;

        while (process) {
            synchronized (counter) {
                if ((counter.threads) == 0) {
                    return;
                } else {
                    try {
                        counter.wait();
                        if (counter.threads == 0) {
                            process = false;
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * distributes data in array on two parts and calls itself for these parts.
     * @param array is data to sort
     * @param left is begin index of data to distribute
     * @param right is end index of data to distribute
     * @param parallel is true, when new thread to call function was situated
     */
    private void multiThreadQuickSort(T array[], int left, int right, boolean parallel) {
        int separator = partOfSort(array, left, right);

        threadsAddition(array, left, separator - 1);
        threadsAddition(array, separator, right);

        if (parallel) {
            synchronized (counter) {
                counter.threads--;
                counter.notify();
            }
        }
    }

    /**
     * adds thread to sort process, if amount of threads is not more, than limit amount of threads.
     * @param array is data to sort
     * @param left is begin index of data to distribute
     * @param right is end index of data to distribute
     */
    private void threadsAddition(T[] array, int left, int right) {
        if (left == right - 1) {
            if (comparator.compare(array[left], array[right]) > 0) {
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            return;
        }

        if (right > left) {
            boolean parallel = false;
            synchronized (counter) {
                if (counter.threads < LIMIT_OF_THREADS) {
                    parallel = true;
                    counter.threads++;
                }
            }

            if (parallel) {
                pool.submit(() -> multiThreadQuickSort(array, left, right, true));
            } else {
                multiThreadQuickSort(array, left, right, false);
            }
        }
    }
}
