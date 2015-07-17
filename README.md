# oracle-jdk8-mooc
Oracle Massive Open Online Course: Java SE 8 Lambdas and Streams

Lambda Expression
=================
Lambda expressions are anonymous functions i.e. methods without a name and not associated with a class:

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
An interface with a single abstract method. Lambda expressions provide the implementation for the abstract method hence
they can be used wherever the type is a functional interface. JDK 8 also allows interfaces to contain:

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
