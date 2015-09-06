/*     / \____  _    ______   _____ / \____   ____  _____
 *    /  \__  \/ \  / \__  \ /  __//  \__  \ /    \/ __  \   Javaslang
 *  _/  // _\  \  \/  / _\  \\_  \/  // _\  \  /\  \__/  /   Copyright 2014-2015 Daniel Dietrich
 * /___/ \_____/\____/\_____/____/\___\_____/_/  \_/____/    Licensed under the Apache License, Version 2.0
 */
package javaslang.collection;

import javaslang.Lazy;
import javaslang.Tuple2;
import javaslang.control.None;
import javaslang.control.Option;
import javaslang.control.Some;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.*;

// DEV-NOTE: it is not possible to create an EMPTY TreeSet without a Comparator type in scope
public final class TreeSet<T> implements SortedSet<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private final RedBlackTree<T> tree;
    private final transient Lazy<Integer> length = Lazy.of(() -> iterator().length());

    private TreeSet(RedBlackTree<T> tree) {
        this.tree = tree;
    }

    public static <T extends Comparable<T>> TreeSet<T> empty() {
        return new TreeSet<>(RedBlackTree.<T> empty());
    }

    public static <T> TreeSet<T> empty(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        return new TreeSet<>(RedBlackTree.empty(comparator));
    }

    public static <T extends Comparable<T>> TreeSet<T> of(T value) {
        return new TreeSet<>(RedBlackTree.of(value));
    }

    public static <T extends Comparable<T>> TreeSet<T> of(Comparator<? super T> comparator, T value) {
        Objects.requireNonNull(comparator, "comparator is null");
        return new TreeSet<>(RedBlackTree.of(comparator, value));
    }

    @SuppressWarnings({ "unchecked", "varargs" })
    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> of(T... values) {
        Objects.requireNonNull(values, "values is null");
        return new TreeSet<>(RedBlackTree.of(values));
    }

    @SuppressWarnings({ "unchecked", "varargs" })
    @SafeVarargs
    public static <T extends Comparable<T>> TreeSet<T> of(Comparator<? super T> comparator, T... values) {
        Objects.requireNonNull(comparator, "comparator is null");
        Objects.requireNonNull(values, "values is null");
        return new TreeSet<>(RedBlackTree.of(comparator, values));
    }

    public static <T extends Comparable<T>> TreeSet<T> ofAll(java.lang.Iterable<? extends T> values) {
        Objects.requireNonNull(values, "values is null");
        return new TreeSet<>(RedBlackTree.ofAll(values));
    }

    public static <T> TreeSet<T> ofAll(Comparator<? super T> comparator, java.lang.Iterable<? extends T> values) {
        Objects.requireNonNull(comparator, "comparator is null");
        Objects.requireNonNull(values, "values is null");
        return new TreeSet<>(RedBlackTree.ofAll(comparator, values));
    }

    /**
     * Creates a TreeSet based on the elements of a boolean array.
     *
     * @param array a boolean array
     * @return A new TreeSet of Boolean values
     */
    public static TreeSet<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Boolean>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Boolean next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a byte array.
     *
     * @param array a byte array
     * @return A new TreeSet of Byte values
     */
    public static TreeSet<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Byte>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Byte next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a char array.
     *
     * @param array a char array
     * @return A new TreeSet of Character values
     */
    public static TreeSet<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Character>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Character next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a double array.
     *
     * @param array a double array
     * @return A new TreeSet of Double values
     */
    public static TreeSet<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Double>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Double next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a float array.
     *
     * @param array a float array
     * @return A new TreeSet of Float values
     */
    public static TreeSet<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Float>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Float next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of an int array.
     *
     * @param array an int array
     * @return A new TreeSet of Integer values
     */
    public static TreeSet<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a long array.
     *
     * @param array a long array
     * @return A new TreeSet of Long values
     */
    public static TreeSet<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Long>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Long next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet based on the elements of a short array.
     *
     * @param array a short array
     * @return A new TreeSet of Short values
     */
    public static TreeSet<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return TreeSet.ofAll(() -> new Iterator<Short>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Short next() {
                return array[i++];
            }
        });
    }

    /**
     * Creates a TreeSet of int numbers starting from {@code from}, extending to {@code toExclusive - 1}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.range(0, 0)  // = TreeSet()
     * TreeSet.range(2, 0)  // = TreeSet()
     * TreeSet.range(-2, 2) // = TreeSet(-2, -1, 0, 1)
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toExclusive the last number + 1
     * @return a range of int values as specified or the empty range if {@code from >= toExclusive}
     */
    public static TreeSet<Integer> range(int from, int toExclusive) {
        return TreeSet.rangeBy(from, toExclusive, 1);
    }

    /**
     * Creates a TreeSet of int numbers starting from {@code from}, extending to {@code toExclusive - 1},
     * with {@code step}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeBy(1, 3, 1)  // = TreeSet(1, 2)
     * TreeSet.rangeBy(1, 4, 2)  // = TreeSet(1, 3)
     * TreeSet.rangeBy(4, 1, -2) // = TreeSet(4, 2)
     * TreeSet.rangeBy(4, 1, 2)  // = TreeSet()
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toExclusive the last number + 1
     * @param step        the step
     * @return a range of long values as specified or the empty range if<br>
     * {@code from >= toInclusive} and {@code step > 0} or<br>
     * {@code from <= toInclusive} and {@code step < 0}
     * @throws IllegalArgumentException if {@code step} is zero
     */
    public static TreeSet<Integer> rangeBy(int from, int toExclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toExclusive || step * (from - toExclusive) > 0) {
            return TreeSet.empty();
        } else {
            final int one = (from < toExclusive) ? 1 : -1;
            return TreeSet.rangeClosedBy(from, toExclusive - one, step);
        }
    }

    /**
     * Creates a TreeSet of long numbers starting from {@code from}, extending to {@code toExclusive - 1}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.range(0L, 0L)  // = TreeSet()
     * TreeSet.range(2L, 0L)  // = TreeSet()
     * TreeSet.range(-2L, 2L) // = TreeSet(-2L, -1L, 0L, 1L)
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toExclusive the last number + 1
     * @return a range of long values as specified or the empty range if {@code from >= toExclusive}
     */
    public static TreeSet<Long> range(long from, long toExclusive) {
        return TreeSet.rangeBy(from, toExclusive, 1);
    }

    /**
     * Creates a TreeSet of long numbers starting from {@code from}, extending to {@code toExclusive - 1},
     * with {@code step}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeBy(1L, 3L, 1L)  // = TreeSet(1L, 2L)
     * TreeSet.rangeBy(1L, 4L, 2L)  // = TreeSet(1L, 3L)
     * TreeSet.rangeBy(4L, 1L, -2L) // = TreeSet(4L, 2L)
     * TreeSet.rangeBy(4L, 1L, 2L)  // = TreeSet()
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toExclusive the last number + 1
     * @param step        the step
     * @return a range of long values as specified or the empty range if<br>
     * {@code from >= toInclusive} and {@code step > 0} or<br>
     * {@code from <= toInclusive} and {@code step < 0}
     * @throws IllegalArgumentException if {@code step} is zero
     */
    public static TreeSet<Long> rangeBy(long from, long toExclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toExclusive || step * (from - toExclusive) > 0) {
            return TreeSet.empty();
        } else {
            final int one = (from < toExclusive) ? 1 : -1;
            return TreeSet.rangeClosedBy(from, toExclusive - one, step);
        }
    }

    /**
     * Creates a TreeSet of int numbers starting from {@code from}, extending to {@code toInclusive}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeClosed(0, 0)  // = TreeSet(0)
     * TreeSet.rangeClosed(2, 0)  // = TreeSet()
     * TreeSet.rangeClosed(-2, 2) // = TreeSet(-2, -1, 0, 1, 2)
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toInclusive the last number
     * @return a range of int values as specified or the empty range if {@code from > toInclusive}
     */
    public static TreeSet<Integer> rangeClosed(int from, int toInclusive) {
        return TreeSet.rangeClosedBy(from, toInclusive, 1);
    }

    /**
     * Creates a TreeSet of int numbers starting from {@code from}, extending to {@code toInclusive},
     * with {@code step}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeClosedBy(1, 3, 1)  // = TreeSet(1, 2, 3)
     * TreeSet.rangeClosedBy(1, 4, 2)  // = TreeSet(1, 3)
     * TreeSet.rangeClosedBy(4, 1, -2) // = TreeSet(4, 2)
     * TreeSet.rangeClosedBy(4, 1, 2)  // = TreeSet()
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toInclusive the last number
     * @param step        the step
     * @return a range of int values as specified or the empty range if<br>
     * {@code from > toInclusive} and {@code step > 0} or<br>
     * {@code from < toInclusive} and {@code step < 0}
     * @throws IllegalArgumentException if {@code step} is zero
     */
    public static TreeSet<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return TreeSet.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return TreeSet.empty();
        } else {
            final int gap = (from - toInclusive) % step;
            final int signum = (from < toInclusive) ? -1 : 1;
            final int bound = from * signum;
            TreeSet<Integer> result = TreeSet.empty();
            for (int i = toInclusive + gap; i * signum <= bound; i -= step) {
                result = result.add(i);
            }
            return result;
        }
    }

    /**
     * Creates a TreeSet of long numbers starting from {@code from}, extending to {@code toInclusive}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeClosed(0L, 0L)  // = TreeSet(0L)
     * TreeSet.rangeClosed(2L, 0L)  // = TreeSet()
     * TreeSet.rangeClosed(-2L, 2L) // = TreeSet(-2L, -1L, 0L, 1L, 2L)
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toInclusive the last number
     * @return a range of long values as specified or the empty range if {@code from > toInclusive}
     */
    public static TreeSet<Long> rangeClosed(long from, long toInclusive) {
        return TreeSet.rangeClosedBy(from, toInclusive, 1L);
    }

    /**
     * Creates a TreeSet of long numbers starting from {@code from}, extending to {@code toInclusive},
     * with {@code step}.
     * <p>
     * Examples:
     * <pre>
     * <code>
     * TreeSet.rangeClosedBy(1L, 3L, 1L)  // = TreeSet(1L, 2L, 3L)
     * TreeSet.rangeClosedBy(1L, 4L, 2L)  // = TreeSet(1L, 3L)
     * TreeSet.rangeClosedBy(4L, 1L, -2L) // = TreeSet(4L, 2L)
     * TreeSet.rangeClosedBy(4L, 1L, 2L)  // = TreeSet()
     * </code>
     * </pre>
     *
     * @param from        the first number
     * @param toInclusive the last number
     * @param step        the step
     * @return a range of int values as specified or the empty range if<br>
     * {@code from > toInclusive} and {@code step > 0} or<br>
     * {@code from < toInclusive} and {@code step < 0}
     * @throws IllegalArgumentException if {@code step} is zero
     */
    public static TreeSet<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        } else if (from == toInclusive) {
            return TreeSet.of(from);
        } else if (step * (from - toInclusive) > 0) {
            return TreeSet.empty();
        } else {
            final long gap = (from - toInclusive) % step;
            final int signum = (from < toInclusive) ? -1 : 1;
            final long bound = from * signum;
            TreeSet<Long> result = TreeSet.empty();
            for (long i = toInclusive + gap; i * signum <= bound; i -= step) {
                result = result.add(i);
            }
            return result;
        }
    }

    @Override
    public TreeSet<T> add(T element) {
        return new TreeSet<>(tree.insert(element));
    }

    @Override
    public boolean hasDefiniteSize() {
        return true;
    }

    @Override
    public boolean isTraversableAgain() {
        return true;
    }

    @Override
    public TreeSet<T> clear() {
        return isEmpty() ? this : new TreeSet<>(tree.clear());
    }

    @Override
    public boolean contains(T element) {
        return tree.contains(element);
    }

    @Override
    public TreeSet<T> distinct() {
        return this;
    }

    @Override
    public TreeSet<T> distinctBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        return TreeSet.ofAll(tree.comparator(), iterator().distinctBy(comparator));
    }

    @Override
    public <U> TreeSet<T> distinctBy(Function<? super T, ? extends U> keyExtractor) {
        Objects.requireNonNull(keyExtractor, "keyExtractor is null");
        return TreeSet.ofAll(tree.comparator(), iterator().distinctBy(keyExtractor));
    }

    @Override
    public TreeSet<T> drop(int n) {
        return TreeSet.ofAll(tree.comparator(), iterator().drop(n));
    }

    @Override
    public TreeSet<T> dropRight(int n) {
        return TreeSet.ofAll(tree.comparator(), iterator().dropRight(n));
    }

    @Override
    public TreeSet<T> dropWhile(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return TreeSet.ofAll(tree.comparator(), iterator().dropWhile(predicate));
    }

    @Override
    public TreeSet<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return TreeSet.ofAll(tree.comparator(), iterator().filter(predicate));
    }

    @Override
    public Option<T> findLast(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return iterator().findLast(predicate);
    }

    @Override
    public <U> TreeSet<U> flatMap(Function<? super T, ? extends java.lang.Iterable<? extends U>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        // TODO: return TreeSet.ofAll(?comparator?, iterator().flatMap(mapper));
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<Object> flatten() {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public <U> U foldRight(U zero, BiFunction<? super T, ? super U, ? extends U> f) {
        Objects.requireNonNull(f, "f is null");
        return iterator().foldRight(zero, f);
    }

    @Override
    public <C> Map<C, TreeSet<T>> groupBy(Function<? super T, ? extends C> classifier) {
        Objects.requireNonNull(classifier, "classifier is null");
        return iterator()
                .groupBy(classifier)
                .map((key, iterator) -> new Map.Entry<>(key, TreeSet.ofAll(tree.comparator(), iterator)));
    }

    @Override
    public T head() {
        if (isEmpty()) {
            throw new NoSuchElementException("head of empty TreeSet");
        } else {
            return tree.min().get();
        }
    }

    @Override
    public Option<T> headOption() {
        return tree.min();
    }

    @Override
    public TreeSet<T> init() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("init of empty TreeSet");
        } else {
            return new TreeSet<>(tree.delete(tree.max().get()));
        }
    }

    @Override
    public Option<TreeSet<T>> initOption() {
        return isEmpty() ? None.instance() : new Some<>(init());
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }

    @Override
    public int length() {
        return length.get();
    }

    @Override
    public <U> TreeSet<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public Tuple2<TreeSet<T>, TreeSet<T>> partition(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return iterator()
                .partition(predicate)
                .map(i1 -> TreeSet.ofAll(tree.comparator(), i1), i2 -> TreeSet.ofAll(tree.comparator(), i2));
    }

    @Override
    public TreeSet<T> peek(Consumer<? super T> action) {
        Objects.requireNonNull(action, "action is null");
        if (!isEmpty()) {
            action.accept(head());
        }
        return this;
    }

    @Override
    public T reduceRight(BiFunction<? super T, ? super T, ? extends T> op) {
        Objects.requireNonNull(op, "op is null");
        return iterator().reduceRight(op);
    }

    @Override
    public TreeSet<T> remove(T element) {
        return new TreeSet<>(tree.delete(element));
    }

    @Override
    public TreeSet<T> removeAll(java.lang.Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        RedBlackTree<T> that = tree;
        for (T element : elements) {
            that = that.delete(element);
        }
        if (that == tree) {
            return this;
        } else {
            return new TreeSet<>(that);
        }
    }

    @Override
    public TreeSet<T> replace(T currentElement, T newElement) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<T> replaceAll(T currentElement, T newElement) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<T> replaceAll(UnaryOperator<T> operator) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<T> retainAll(java.lang.Iterable<? extends T> elements) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public Tuple2<TreeSet<T>, TreeSet<T>> span(Predicate<? super T> predicate) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<T> tail() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("tail of empty TreeSet");
        } else {
            return new TreeSet<>(tree.delete(tree.min().get()));
        }
    }

    @Override
    public Option<TreeSet<T>> tailOption() {
        return isEmpty() ? None.instance() : new Some<>(tail());
    }

    @Override
    public TreeSet<T> take(int n) {
        return TreeSet.ofAll(tree.comparator(), iterator().take(n));
    }

    @Override
    public TreeSet<T> takeRight(int n) {
        return TreeSet.ofAll(tree.comparator(), iterator().takeRight(n));
    }

    @Override
    public TreeSet<T> takeWhile(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        return TreeSet.ofAll(tree.comparator(), iterator().takeWhile(predicate));
    }

    @Override
    public <T1, T2> Tuple2<TreeSet<T1>, TreeSet<T2>> unzip(Function<? super T, Tuple2<? extends T1, ? extends T2>> unzipper) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public <U> TreeSet<Tuple2<T, U>> zip(java.lang.Iterable<U> that) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public <U> TreeSet<Tuple2<T, U>> zipAll(java.lang.Iterable<U> that, T thisElem, U thatElem) {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }

    @Override
    public TreeSet<Tuple2<T, Integer>> zipWithIndex() {
        throw /*TODO*/ new UnsupportedOperationException("TODO");
    }
}
