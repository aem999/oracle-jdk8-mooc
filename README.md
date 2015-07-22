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
A *stream* is a sequence of elements supporting aggregation operations. Unlike a collection, it is not a data structure 
that stores elements. Instead, a stream is used to carry values from a *source* through a *pipeline* and is calculated 
on demand.


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
The stream *source* may be a collection, an array, a generator function, or an I/O channel. There are 71 methods in 15 
classes in JDK 8 that can be used to provide a stream source.


Stream Pipeline
---------------
A *pipeline* contains the following components:

- a source
- zero or more intermediate operations, such as filter, that produce a new stream.
- a terminal operation, such as `forEach`, that produces a non-stream result, such as a primitive value, a collection, 
or in the case of `forEach`, no value at all.

The pipeline is only evaluated when the terminal operation is called and then:
- the operations may be executed sequentially or in parallel
- intermediate operations may be merged
- the JVM may perform other optimisations e.g. a distinct stream passed to `distinct()` is a noop


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
   application of a function f to an initial element seed, producing a ``Stream consisting of seed, f(seed), f(f(seed)), etc.
