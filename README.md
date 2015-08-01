# oracle-jdk8-mooc
Notes from Oracle Massive Open Online Course: Java SE 8 Lambdas and Streams

Lambda Expression
=================
*Lambda expressions* are anonymous functions i.e. methods without a name and not associated with a class:

    (parameters) -> expression
    or
    (parameters) -> { statements; }
         
    * a lambda statement                                            e.g. (int x) -> { return x + 1; }
    * a lambda expression (does not need return keyword or braces)  e.g. (int x) -> x + 1
    * the data type of the parameters can be omitted                e.g. (x) -> x + 1
    * a single parameter lambda does not need brackets              e.g. x -> x + 1
    * a no parameter lambda needs empty brackets                    e.g. () -> System.out.println("hello");

       
Functional Interfaces
--------------------
A *functional interface* is an interface with a single abstract method. Lambda expressions provide the implementation 
for the abstract method hence they can be used wherever the type is a functional interface. JDK 8 also allows interfaces
to contain:

* default methods
* static methods
* `@FunctionalInterface` annotation to allow compiler to check that the interface is really a functional interface

A number of useful functional interfaces are supplied in JDK8 (in `java.util.function`) unless otherwise specified below:

#### Runnable
Takes _no_ inputs and returns _no_ results:

Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
None           | None    | java.lang.Runnable           | void run()
                                                        
#### Consumers                                       
Take _one_ input and return _no_ results:            
                                                        
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T              | None    | Consumer\<T>                 | void accept(T t)
double         | None    | DoubleConsumer               | void accept(double value)
int            | None    | IntConsumer                  | void accept(int value)
long           | None    | LongConsumer                 | void accept(long value)
                                                        
#### BiConsumers                                     
Take _two_ inputs and return _no_ results:           
                                                        
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T, U           | None    | BiConsumer\<T, U>            | void accept(T t, U u)
T, double      | None    | ObjDoubleConsumer\<T>        | void accept(T t, double value)
T, int         | None    | ObjIntConsumer\<T>           | void accept(T t, int value)
T, long        | None    | ObjLongConsumer\<T>          | void accept(T t, long value)
                                                        
#### Suppliers                                       
Take _no_ inputs and return a result:            
                                                        
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
None           | T       | Supplier\<T>                 | T get()
None           | double  | DoubleSupplier               | double get()
None           | int     | IntSupplier                  | int get()
None           | long    | LongSupplier                 | long get()
None           | boolean | BooleanSupplier              | boolean get()
                                                        
#### Functions                                       
Take _one_ input and return a result:            
                                                        
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T              | R       | Function\<T, R>              | R apply(T t)
double         | R       | DoubleFunction               | R apply(double value)
int            | R       | IntFunction                  | R apply(int value)
long           | R       | LongFunction                 | R apply(long value)
T              | double  | ToDoubleFunction\<T>         | double apply(T t)
T              | int     | ToIntFunction\<T>            | int apply(T t)
T              | long    | ToLongFunction\<T>           | long apply(T t)
double         | int     | DoubleToIntFunction          | int applyAsInt(double value)
double         | long    | DoubleToLongFunction         | long applyAsLong(double value)
int            | double  | IntToDoubleFunction          | double applyAsDouble(int value)
int            | long    | IntToLongFunction            | long applyAsLong(int value)
long           | double  | LongToDoubleFunction         | double applyAsDouble(long value)
long           | int     | LongToIntFunction            | int applyAsInt(long value)
               
#### Bi Functions
Take _two_ inputs and return a result:
               
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T, U           | R       | BiFunction\<T, U, R>         | R apply(T t, U u)
T, U           | double  | ToDoubleBiFunction\<T, U>    | double applyAsDouble(T t, U u)
T, U           | int     | ToIntBiFunction\<T, U>       | int applyAsInt(T t, U u)
T, U           | long    | ToLongBiFunction\<T, U>      | long applyAsLong(T t, U u)
               
#### Operators
Take _one_ input and return a result of the same type:
               
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T              | T       | UnaryOperator\<T>            | T apply(T t)
double         | double  | DoubleUnaryOperator          | double applyAsDouble(double operand)
int            | int     | IntUnaryOperator             | int applyAsInt(int operand)
long           | long    | LongUnaryOperator            | long applyAsLong(long operand)
               
#### Binary Operators
Take _two_ inputs of the same type and return a result of the same type:
               
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T, T           | T       | BinaryOperator\<T>           | T apply(T left, T right)
double, double | double  | DoubleBinaryOperator         | double applyAsDouble(double left, double right)
int, int       | int     | IntBinaryOperator            | int applyAsInt(int left, int right)
long, long     | long    | LongBinaryOperator           | long applyAsLong(long left, long right)

#### Predicates
Take _one_ input and return a boolean result:
               
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T              | boolean | Predicate\<T>                | boolean test(T t)
double         | boolean | DoublePredicate              | boolean test(double value)
int            | boolean | IntPredicate                 | boolean test(int value)
long           | boolean | LongPredicate                | boolean test(long value)

#### Bi Predicates
Take _two_ inputs and return a boolean result:
               
Parameters     | Output  | Functional Interface         | Method
-------------- | ------  | ---------------------------- | ------
T              | boolean | BiPredicate\<T, U>           | boolean test(T t, U u)


Method References
-----------------
A *method reference* is a shorthand means for referring to an existing method and using it as a lambda expression. There
are 3 types of *method references*:

1) Static methods:

    Lambda:             (args) -> ClassName.staticMethod(args)          e.g.    (String s) -> System.out.println(s)
    Method Reference:   ClassName::staticMethod                                 System.out::println
    
2) Instance methods of an arbitrary type:

    Lambda:             (arg0, others) -> arg0.instanceMethod(others)   e.g.    (String s, int i) -> s.substring(i)
    Method Reference:   ClassName::instanceMethod                               String:substring

3) Instance methods of an existing object:

    Lambda:             (args) -> obj.instanceMethod(args)              e.g.    (Axis a) -> getLength(a)              
    Method Reference:   obj::instanceMethod                                     this::getLength


Constructor References
----------------------
A *constructor reference* is a shorthand means for referring to a class constructor and using it as a lambda expression:

    Lambda:                 (int value) -> new Integer(value)
    Constructor Reference:  Integer::new
    
    Lambda:                 () -> new ArrayList<String>()
    Constructor Reference:  ArrayList<String>::new
    
    
Local Variable Capture
----------------------
Lambda expressions can access final or *effectively final* local variables from the surrounding scope. An *effectively 
final* variable is one that is assigned once even if not explicitly declared *final*. The function is bound to the 
value of the local variable forming a *closure*:

Effectively final:

    String s = "Started";   // s is effectively final
    new Thread(() -> System.out.println(s)).start();

Explicitly final:
    
    final String s = "Started";
    new Thread(() -> System.out.println(s)).start();

    
'this' Keyword
--------------
The `this` keyword in a lambda expression refers to the *main* class.  As a lambda is an anonymous function not 
associated with any class there cannot be a`this` reference for the lambda itself (even though the compiler may create 
an inner class to hold the lambda).

Note - the compiler will insert a reference to `this` for you if required which can be dangerous! e.g.

    class DataProcesssor {
        private int currentValue;
    
        public void process() {
            List<Double> values = Arrays.asList(1.1, 2.3, 3.5);
            values.forEach(d -> System.out.println(currentValue++ + ": " + d));
        }
    }

The above should not compile because `currentValue` is not effectively final but the compiler actually inserts a 
reference to `this` which is effectively final. Hence, the value of `this` is bound to the lambda making the closure and
so `currentValue` can then be accessed using the variable `this`:

     class DataProcesssor {
         private int currentValue;
     
         public void process() {
             List<Double> values = Arrays.asList(1.1, 2.3, 3.5);
             values.forEach(d -> System.out.println(this.currentValue++ + ": " + d));
         }
     }
    
JDK 8 methods that use lambdas
-----------------------------
Several new useful methods have been added to the JDK 8 that can use lambdas.

#### Iterable Interface
Iterable.forEach(Consumer c):
    
    myList.forEach(System.out::println)
    
    
#### Collection Interface
Collection.removeIf(Predicate p):

    List<String> myList = ...
    myList.removeIf(s -> s.length() == 0);
    
#### List Interface
List.replaceAll(UnaryOperator o)

    List<String> myList = ...
    myList.replaceAll(String::toUpperCase)
    
List.sort(Comparator c)  // replaces Collections.sort(List l, Comparator c)
     
    List<String> myList = ...
    myList.sort((x, y) -> x.length() - y.length());
    

Lazy Evaluation
---------------
Lambda expressions provide a mechanism for lazy evaluation of code, i.e. code whose execution is deferred until actually
needed. Logging is a good example where this can be useful:

    logger.debug(createComplexMessage())        // complex message will be created even if not needed

New logger method now takes a Supplier function:

    logger.debug(() -> createComplexMessage())  // complex message will only be created if supplier.get() is called

           
Streams
=======
A *stream* is a sequence of elements supporting sequential or parallel aggregation operations. Unlike a collection, a 
stream is not a data structure that stores elements. Instead, a stream is used to carry values from a *source* through a
*pipeline* and is calculated on demand. Typically they are stateless.


Streams vs Collections
----------------------

Collections                                | Streams
-----------                                | -------
an in-memory data structure                | an abstraction
elements created before they are added     | elements created on demand
external iteration (performed by the user) | internal iteration (handled by the stream itself)
finite size                                | may be infinite
sequential processing                      | sequential or parallel processing  


Stream Source
-------------
A stream *source* may be a collection, an array, a generator function, or an I/O channel. There are 71 methods in 15 
classes in JDK 8 that can be used to provide a stream source.


Stream Pipeline
---------------
A *pipeline* contains the following components:

- a source
- zero or more intermediate operations, such as filter, that produce a new stream.
- a terminal operation, such as `forEach`, that produces a non-stream result, such as a primitive value, a collection, 
or in the case of `forEach`, no value at all.

The pipeline is only evaluated when the terminal operation is called and then:

- the operations may be evaluated lazily
- the operations may be executed sequentially or in parallel
- intermediate operations may be merged
- the JVM may perform other optimisations e.g. a distinct stream passed to `distinct()` is a redundant operation

Intermediate operations must be non-interfering i.e. must not modify the objects in the stream otherwise a 
`ConcurrentModificationException` will be thrown. However, new objects are allowed to be created.


Primitive Streams
-----------------
By default streams produce a sequence of object references which can lead to unnecessary boxing and unboxing when 
converting between objects and primitives. Hence, JDK 8 also provides 3 primitive streams to improve stream efficiency 
- `IntStream`, `DoubleStream`, `PrimitiveStream`:

    int highScore = students.stream()
        .filter(s -> s.graduationYear() == 2015)
        .mapToInt(s -> s.getScore())            // produces a stream of int values so no boxing/unboxing required
        .max()

 
Collection Interface
--------------------
- `Collection.stream()` - provides a sequential sequence of elements
- `Collection.parallel()` - provides a possibly parallel sequence of elements (uses the `fork/join` framework). This is
  the class that can provide a parallel stream directly 


Arrays Class
------------
- `Arrays.stream(T[] array)` - provides a sequential sequence of references to objects of type T
- `Arrays.stream(double[] array)` - provides a sequential sequence of doubles
- `Arrays.stream(int[] array)` - provides a sequential sequence of ints
- `Arrays.stream(long[] array)` - provides a sequential sequence of longs


Files
-----
- `Files.find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options)`
   Provides a lazily populated stream of `File` references that match the specified `BiPredicate`
- `Files.list(Path dir)`
   Provides a lazily populated stream of entries for the specified directory
- `Files.lines(Path dir)`
   Provides a lazily populated stream of strings that are the lines in the specified file
- `Files.walk(Path start, FileVisitOption... options)`
   Provides a lazily populated stream of `File` references walking from a given path
   

Random Numbers
--------------
There are 3 related classes for producing finite or infinite streams of random numbers:

- `Random` - produce a stream of pseudorandom `doubles`, `ints` or ``longs
- `ThreadLocalRandom` - as `Random` but offers better performance in multithreaded designs
- `SplittableRandom` - as `Ramdom` but can be split across threads without sharing any state

These classes allow four versions of `double`, `int` or `long` streams to be produced:

- finite
- infinite
- with a seed
- without a seed

=> 36 possible sources of random number streams


Miscellaneous Classes
---------------------
- `ZIPFile.stream()` - returns an ordered Stream of entries in the ZIP file
- `JARFile.stream()` - returns an ordered Stream of entries in the JAR file
- `BufferedLines.lines()` - returns a lazily populated stream of strings that are the lines read by the reader
- `Pattern.splitAsStream()` - returns a stream of matches of a pattern
- `CharSequence.chars()` - returns an `IntStream` of char values from a sequence
- `CharSequence.codePoints()` - returns an `IntStream` of Unicode code points from a sequence
- `BitSet.streams()` - returns a stream of indices for which the `BitSet` contains a bit in the set state


Stream Static Methods
---------------------
- `DoubleStream`, `IntStream`, `LongStream` - primitive specialisations of the `Stream` interface
- `Stream.concat(stream, stream)` - returns a lazily concatenated `Stream` whose elements are all the elements of the 
   first stream followed by all the elements of the second stream. The resulting stream is ordered if both of the input 
   streams are ordered, and parallel if either of the input streams is parallel
- `Stream.empty()` - returns an empty sequential `Stream`
- `Stream.of(T... values)` - returns a sequential ordered stream containing the specified values.
- `IntStream.range(int, int)` - returns a sequential ordered `IntStream` from start (inclusive) to end (exclusive) 
   incrementing by 1.
- `IntStream.range(int, int)` - returns a sequential ordered `IntStream` from start (inclusive) to end (inclusive) 
   incrementing by 1.
- `Stream.generate(Supplier<T> s)` - returns an infinite sequential unordered `Stream` where each element is generated 
   by the provided Supplier.
- `Stream.iterate(T seed, UnaryOperator<T> f)` - returns an infinite sequential ordered `Stream` produced by iterative 
   application of a function f to an initial element seed, producing a `Stream` consisting of seed, f(seed), f(f(seed)), etc.


Intermediate Operations
-----------------------
*Intermediate* operations:

- transform a stream into another stream without modfying the original elements.
- are always evaluated lazily

Examples:

- filtering
- mapping
- sorting
- observing


Intermediate Operations That Reduce The Size Of A Stream
--------------------------------------------------------
- `Stream.distinct()` - returns a stream with no duplicate elements
- `Stream.filter(Predicate)` - returns a stream containing elements matching the predicate
- `Stream.skip(long n)` - returns a stream ignoring the first n elements of the input stream
- `Stream.limit(long n)` - returns a stream containing the first n elements of the input stream


Map Operation
-------------
Performs a *one-to-one* transformation of elements in a stream:

- `Stream.map(Function mapper)` - returns a stream where the supplied mapping function has been applied to each element
- `Stream.mapToDouble(ToDoubleFunction mapper)` - returns a stream of `double` primitives using the supplied mapping function
- `Stream.mapToInt(ToIntFunction mapper)` - returns a stream of `int` primitives using the supplied mapping function
- `Stream.mapToLong(ToLongFunction mapper)` - returns a stream of `long` primitives using the supplied mapping function


FlatMap Operation
-----------------
Performs a *one-to-many* transformation of elements in a stream and then 'flattens' the results into a new stream:

- `Stream.flatMap(Function mapper)` - returns a stream where the supplied mapping function has been applied to each element
  of this stream to produce a mapped stream which is then placed in the new stream. If the mapped stream is null an empty
  stream is added to the result stream instead.
- `Stream.flatMapToDouble(Function mapper)` - as `flatMap()` but returns a stream of `double` primitives
- `Stream.flatMapToInt(Function mapper)` - as `flatMap()` but returns a stream of `int` primitives
- `Stream.flatMapToLong(Function mapper)` - as `flatMap()` but returns a stream of `long` primitives


Sorting/Unsorting Operations
----------------------------
- `Stream.sort()` - returns a stream sorted in natural order
- `Stream.sort(Comparator c)` - returns a stream sorted into the order determined by the comparator
- `Stream.unordered()` - returns an equivalent stream that is unordered. May return itself if the stream was already unordered.


Observing Stream Elements
-------------------------
Observe elements of a stream as they pass *without* modifying them:

- `Stream.peek(Consumer c)` - returns an output stream that is identical to the input stream but each element is passed
to the `accept` method of the consumer. Useful for debugging and doing more than one thing with a stream.


Terminal Operations
-------------------
*Terminal* operations are ones that produce a result:
- aggregate functions like `max()`, `count()`

or a side-effect: 
- `forEach(Consumer c)`


Terminal Operations For Matching
--------------------------------
- `Stream.findFirst()` - returns an `Optional` containing the first element in an ordered stream, any element for an 
unordered stream or an empty `Optional` for an empty stream
- `Stream.findAny()` - returns an `Optional` describing some element of the stream, or an empty `Optional` if the stream 
is empty.
- `Stream.allMatch(Predicate predicate)` - returns `true` if all elements in the stream match the `Predicate` 
- `Stream.anyMatch(Predicate predicate)` - returns `true` if any elements in the stream match the `Predicate`
- `Stream.noneMatch(Predicate predicate)` - returns `true` if none of the elements in the stream match the `Predicate`


Reduction Operations
--------------------
A reduction operation (also called a fold) takes a sequence of input elements and combines them into a single summary 
result by repeated application of a combining operation, such as finding the sum or maximum of a set of numbers, or 
accumulating elements into a list. The streams classes have multiple forms of general reduction operations:

- collectors - perform reductions into a mutable container
- reducers - perform reductions into a single value of the same type


Collectors
----------
*Collectors* accumulate entries into a mutable result container, and optionally perform a final transform on the result.
A Collector is specified by four functions that work together:

- `supplier()` - creation of a new result container
- `accumulator()` - incorporating a new data element into a result container
- `combiner()` - combining two result containers into one
- `finisher()` - performing an optional final transform on the container

Examples:

- `Stream.toArray()` - returns an array containing the elements of the stream
- `Stream.collect(Collector c)` - returns a mutable result container, such as a `Collection` or `StringBuilder`, using the
 specified `Collector` to accumulate elements from the stream into the results container
- `Collectors.toCollection(Supplier collectorFactory)` - returns a `Collector` that accumulates the input elements into 
a new `Collection`, in encounter order
- `Collectors.toList()` - returns a `Collector` which collects all the input elements into a new `List`, in encounter order
- `Collectors.toSet()` - returns a `Collector` which collects all the input elements into a new `Set` (eliminates duplicates),
- `Collectors.toMap(Function keyMapper, Function valueMapper)` - returns a `Collector` which collects elements into a `Map` 
whose keys and values are the result of applying mapping functions to the input elements. Duplicate keys are not allowed.
`Function.identity()` can be  used to place the element itself into the map
- `Collectors.toMap(Function keyMapper, Function valueMapper, BinaryOperator merge)` - returns a `Collector` which collects
elements into a `Map` but this version uses a `BinaryOperator` to merge values for duplicate keys.
- `Collectors.groupingBy(Function classifier)` - returns a `Collector` implementing a "group by" operation on the input
elements using a classification function, and returning the results in a `Map<K, List<V>>`. The map key is specified by 
the classifier and the value is a list of the input elements matching the key
- `Collectors.groupingBy(Function classifier, Collector downstream)` - returns a `Collector` implementing a cascaded 
"group by" operation on the input elements, grouping them according to a classification function, and then performing a 
reduction operation on the values associated with a given key using the specified downstream `Collector`.
- `Collectors.joining()` - returns a `Collector` that concatenates the input elements into a `String`, in encounter order
- `Collectors.joining(CharSequence delimiter)` - returns a `Collector` that concatenates the input elements into a `String`,
separated by the specified delimiter, in encounter order
- `Collectors.joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)` - returns a `Collector` that 
concatenates the input elements, separated by the specified delimiter, with the specified prefix and suffix, in encounter order
- `Collectors.averagingInt(ToIntFunction mapper)` - returns a `Collector` that averages the results provided by the mapper
- `Collectors.summarizingInt(ToIntFunction mapper)` - returns a `Collector` which applies an int-producing mapping function
to each input element, and returns summary statistics for the resulting values
- `Collectors.summingInt(ToIntFunction mapper)` - returns a `Collector` that produces the sum of a integer-valued function
applied to the input elements. If no elements are present, the result is 0. (Equivalent to a `map()` then `sum()`)
- `Collectors.maxBy(Comparator c)` - returns a `Collector` that produces the maximum value according to the `Comparator` 
- `Collectors.minBy(Comparator c)` - returns a `Collector` that produces the minimum value according to the `Comparator` 
- `Collectors.reduce(BinaryOperator op)` - returns a `Collector` which performs a reduction of its input elements using the
specified `BinaryOperator`
- `Collectors.partitioningBy(Predicate predicate)` - returns a `Collector` which partitions the input elements into 2 groups 
according to a `Predicate`, and organizes them into a `Map<Boolean, List<T>>`
- `Collectors.mapping(Function mapper, Collector downstream)` - returns a `Collector` which applies the mapping function
to the input elements and provides the mapped results to the downstream `Collector`


Reducers
--------
*Reducers* accumulate the elements of this stream into a single value, using an *associative*, *stateless*, 
*non-interfering* accumulation function. The accumulator takes a partial result and the next value and returns a new 
partial result. The reduction is like a recursive function but without the resource overhead.

Examples:

- `Stream.reduce(BinaryOperator accumulator)` - returns an `Optional` containing the reduced value of this stream 
according to the provided `accumulator` or an empty `Optional` if there is none.
- `Stream.reduce(T identity, BinaryOperator accumulator)` - performs the reduction using an initial value T. 
Note - returns a value instead of an optional
- `Stream.reduce(T identity, BiFunction accumulator, BinaryOperator combiner)` - to perform a combined map and reduce
i.e. the `BiFunction` performs the map and the `BinaryOperator` performs the reduce
- `Files.lines(path).max(Comparator.comparingInt(String::length)).get()` - returns the longest line in a file


Numerical Results
-----------------
- `Stream.count()` - returns a `long` representing the count of elements in the stream. This is a special case of a 
reduction and is equivalent to `return mapToLong(e -> 1L).sum();`
- `Stream.max(Comparator comparator)` - returns an `Optional` containing the maximum element of this stream according to 
the provided `Comparator` or an empty `Optional` if the stream is empty. This is a special case of a reduction.
- `Stream.min(Comparator comparator)` - returns an `Optional` containing the minimum element of this stream according to 
the provided `Comparator` or an empty `Optional` if the stream is empty. This is a special case of a reduction.

Primitive type streams (`DoubleStream`, `IntStream`, `LongStream`):

- `average()` - returns an `OptionalDouble` containing the arithmetic mean of the stream, or an empty `OptionalDouble`
if the stream is empty
- `sum()` - returns the sum of elements in the stream. This is a special case of a reduction and is equivalent to 
`return reduce(0, T::sum)` where `T` is one of `Double`, `Integer`, `Long`


Iteration
---------
- `Stream.forEach(Consumer action)` - performs an action for each element of this stream. For parallel stream pipelines,
this operation does not guarantee to respect the element order of the stream
- `Stream.forEachOrdered(Consumer action)` - performs an action for each element of this stream in order (if one exists).
For parallel streams the actions may be performed in different threads but by using a *happens-before* relationship the 
ordering is respected


Optional Class
--------------
A container object for containing a possibly null value. This is a value-based class so use of identity-sensitive 
operations (including reference equality (==), identity hash code, or synchronization) on instances of `Optional` may 
have unpredictable results and should be avoided.

- `get()` - returns a value is present otherwise throws `NoSuchElementException`
- `isPresent()` - returns true if there is a value present
- `ifPresent(Consumer consumer)` - invokes the specified consumer with the value if present otherwise does nothing
- `filter(Predicate predicate)` - returns the `Optional` if the value matches the predicate otherwise returns an empty `Optional`
- `map(Function mapper)` - returns an `Optional` containing the result of applying the mapping function to the value of 
this `Optional`, if a value is present, otherwise returns an empty Optional
- `flatMap(Function mapper)` - like `map()` except does not wrap the result in an `Optional` as the mapper function 
already returns an `Optional`. This prevents nested results like `Optional<Optional<String>>`


Infinite Streams
----------------
To terminate there are 2 terminal functions available:
- `findFirst()` - finds the first element
- `findAny()` - finds any element (non-determinstic parallel result)

To process results without terminating the stream:
- `forEach()` - consumes each element but does not terminate the stream (non-deterministic parallel order). Should *not* be used to modify the elements
- `forEachOrdered()` - consumes each element but does not terminate the stream (deterministic parallel order). Should *not* be used to modify the elements


Parallel Streams
----------------
*Parallel* streams are implemented internally using the *fork-join* framework. The default `ForkJoinPool` has a 
parallelism equal to the number of available processors as given by `Runtime.availableProcessors()`. To use a different
level of parallelism there are 2 options:

- Change the parallelism level of the common `ForkJoinPool` by setting the system property `java.util.concurrent.ForkJoinPool.common.parallelism`
to a non-negative value indicating the thread pool size
- Use a custom `ForkJoinPool` e.g.
    
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        List primes = forkJoinPool.submit(() ->
            range(1, 1_000_000).parallel().boxed().filter(Primes::isPrime).collect(Collectors.toList())
        ).get();


Parallel Streams Uage Guidelines
--------------------------------
*Parallel* streams have an additional overhead compared to sequential streams so do not assume they will always be quicker:

### Data Structures
`ArrayList` - *GOOD* data structure for parallel processing
`HashSet`, `TreeSet` - *OK* data structure for parallel processing
`LinkedList` - *POOR* data structure for parallel processing

### Operations
`filter()`, `map()` - *EXCELLENT* for decomposing into parallel tasks
`sorted()`, `distinct()` - *POOR* for decomposing into parallel tasks

### Data Size
N - size of dataset
Q - Cost per element through the Stream pipeline
N * Q - Total cost of pipeline operations

N is known, but Q needs to be estimated. Profile to be sure.


Debugging Streams
-----------------
Streams provide a high level of abstraction which makes code clear and easy to understand, however the downside is that
this makes lambdas and streams difficult to debug:

- a lot of work is taking place in library code which is not visible in our code
- breakpoints in our code can only be set at a very coarse level
- stream operations are merged to improve efficiency

### Using `peek()`

We can use `peek()` to find out what is happening between methods:

    List<String> sortedWords = reader.lines()           // Lines from file
        .flatMap(line -> Stream.of(line.split(REGEXP))  // Words from file
        .map(String::toLowerCase)                       // In lower case
        .peek(System.out::println)
        .distinct()                                     // Remove duplicates
        .sort((x, y) -> x.length() – y.length())        // Sort by length
        .collect(Collectors.toList());                  // Collect to list

We can also use peek() to set up a breakpoint between methods:

    List<String> sortedWords = reader.lines()           // Lines from file
        .flatMap(line -> Stream.of(line.split(REGEXP))  // Words from file
        .map(String::toLowerCase)                       // In lower case
        .peek(s -> s)                                   // No-op lambda to provide line for breakpoint
        .distinct()                                     // Remove duplicates
        .sort((x, y) -> x.length() – y.length())        // Sort by length
        .collect(Collectors.toList());                  // Collect to list


Debugging Lambdas
-----------------
Lambdas are not compiled into inner classes, instead they are compiled into `invokeDynamic` JVM calls and the implementation
is decided at runtime. This makes debugging harder. A solution is:

- extract the code in the lambda expression and move it into a separate method
- replace the lambda expression with the method reference
- set breakpoints in the new method


