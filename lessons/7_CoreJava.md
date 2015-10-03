# Title: Core Java

*Instructor: Alex Samuel*  

Date: October 4th, 2015

We'll review the basics of Java classes and move on to some advanced class-related language features that Java provides.  Mastery of these features is necessary for object oriented programming in Java.

## Classes

Let's start with an example: the Google Play store.  Let's write our own.

```java
package nyc.c4q.playstore;

import java.util.ArrayList;
import java.util.List;

public class Store {
    Store() {
        products = new ArrayList<Product>();
    }
    List<Product> products;
}
```

Here we have a plain, ordinary Java **class**.  It has one **method** and one **field**.  The method happens to be a _constructor_: it is called when creating a new instance.  We'll discuss these more later.

Each method has,

1. A name.
2. Zero or more **parameters**, each of which has a name and a type.
3. A single **return type**, the type of the value it returns.  The return type may be `void` for no return value.
4. A **body**, containing the code to execute.

A function's name and the number and types of its parameters, taken together, make up the function's **signature**.  Note that the parameter names are not part of the signature, nor is the return rype.

> :star: **Hint:** The term **argument** refers to the value passed _into_ a method, while a parameter is the value _received by_ a method.  In this example,
> 
> ```java
> double square(double x) { return x * x; }
> ```
> the method `square` has a parameter named `x`, and, 
> 
> ```java
> double y = square(2.65);
> ```
> we're calling `square` with the argument 2.65.
> 
> Some people use these terms interchangeably, though.

This is a fully-formed class, and we can **instantiate** it, or create an **instance** of the class, with the `new` keyword.

```java
public static void main(String[] args) {
    Store store = new Store();
}
```

## Abstract methods and classes

What's the product?  It could be an app, a song, or even a physical product, so for now we model it with "least common denominator" class that contains only the attributes we expect _all_ products in the store to have.

```java
package nyc.c4q.playstore;

public abstract class Product {
    abstract long getId();
    abstract String getName();
    abstract double getPrice();
}
```

These methods don't have bodies and are declared `abstract`; we call them **abstract methods**.  Think of them as access points to methods that will be provided elsewhere.  In fact, different products might provide different code for each method.  For example, we might want to look up some of this data in a database, or get it from an API, _etc._

While we'll need to provide them at some point, someone _calling_ them doesn't need to care about where they came from.  

This class needs to be an **abstract** class, since it has abstract members.  

A class must be `abstract` to have abstract methods.  An abstract class may not be instantiated.

In fact, maybe we _do_ want to commit to a fixed ID for each product. Since `Product` is an abstract class, it can have **concrete** (not abstract) methods and fields as well.

```java
public long getId() {
    return id;
}

private long id;
```

## Accessors

The `getName()` and `getPrice()` methods in the example above are called **getters**.  

> :star: **Hint:** It is almost always best to make a field `private`, and provide a getter to retrieve its value.  

It is a very important convention in Java that the name of a getter starts with `get`.  The only exception to this is a getter that returns a `bool`; such a getter should start with `is`, such as `isFinished()` or `isReady()`.

If you wish to provide the ability to _change_ a value, use a **setter**.  A setter has a name that starts with `set` and takes a single parameter.

Getters and setters together are called **accessors**.

## Final fields

In fact, we want a product's ID never to change.  To emphasize this, we can use a **final** field.

```java
public Product(long id) {
    this.id = id;
}

private final long id;
```

Since this is a `final` field, it must be initialized in the constructor; it can't be set or reset later.

Note the `this.id` notation: this distinguishes the field named "id" from the constructor parameter named "id".  This is a common pattern for initializing fields.

A Java `final` field is an example of **immutable state**: data that cannot be changed after it is defined.

> :star: **Hint:**  Use immutable fields as much as you can.  This allows you to know exactly when the value of a field was defined, at any point in the program: it must have been defined when the instance was created, and cannot have been changed since then.  
>
> It can also help the Java compiler compile your code more efficiently.

## Subclasses / inheritance

Let's make some product classes now, starting with an app class.  The keyword `extends` is used for this.

```java
package nyc.c4q.playstore;

public class App extends Product {
    public App(long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    private final String name;
    private final double price;
}
```

We use lots of terms to describe the relationship between `Product` and `App`:

- `Product` **extends** `App`
- `Product` **inherits from** `App`
- `Product` is a **subclass** or **subtype** of `App`
- `App` is the **base class** or **superclass** of `Product`
- `Product` and `App` are **parent** and **child**, respectively

Note some key points here:

- `App` has the same `getName()` and `getPrice()` methods that its superclass `App` does.  When a subclass has a method with the same name and parameter types as a base class method, we call it **overriding** a method.  (Don't confuse this with _overloading_, which we'll discuss later.)

- The overriding methods have the same parameters as the superclass versions.  

- The overriding methods are marked with the `@Override` annotation.  This is not required but _highly suggested_, as it allows the compiler to check for errors.

- Because the constructor of `Product` takes one argument, we _must_ call it from the constructor of `App`.  We do this with the `super` keyword, which referrs to the superclass.  This is always required in a subclass constructor, _unless_ the superclass has a constructor that takes no arguments, in which case it may be omitted.

If a class does not extend any other type, it automatically extends `java.lang.Object`.  Thus, all classes are either directly or indirectly subclasses of `Object`.


## Immutabile classes

Observe that _all_ of the fields in this class are `final`.  We call this type of class an **immutable class**.  Once you create an instance of it, you can't change anything about it.  An immutable class, obviously, will have getters but not setters.

> :dart: **Exercise**: Design an immutable class for holding a U.S. postal address.


## Final methods

What if we tried to store the ID here in addition to the base class?

```java
@Override
public long getId() {
    return 1000;
}
```

This is allowed by Java--but undesireable.  We can prevent it by making the superclass method a **final method**.

```java
public final long getId() {
    return id;
}
```

A final method may not be overridden.

## Final classes

A class may be a **final class** as well: another class may not extend a final class.

```java
final class Store {
  // ...
}
```

Obviously, a class may not be both `final` and `abstract`, as that would be useless: we can't instantiate it, since it is abstract, but we can't extend it with a concrete class either.

> :dart: **Exercise:** Design a class model to represent real estate listings.  Your model should handle apartments, houses, apartment buildings, and empty lots for sale.

## Interfaces

An **interface** is a very restricted form of an abstract class.  It may contain only,

1. Abstract methods.  These do not have to be marked `abstract`.
2. Constant fields (see below).

Further, an interface may not extend a class; it may, however, extend another interface.

```java
public interface Rateable {
    void rate(int stars);
    int getRating();
}
```

All methods and fields of an interface are automatically `public`, and other access modifiers (`protected` or `private`) are not allowed.

> :dart: **Exercise:** Given the definition of an interface, why do only public methods and fields make sense?

An interfaces is used in much the same way as an abstract class, except we don't say that a class "extends" it.  Instead, we say that a class **implements** it, using the special `implements` keyword.  

```java
public class App extends Product implements Rateable {
    // ...
    
    @Override
    public void rate(int stars) {
        this.stars = stars;
    }

    @Override
    public int getRating() {
        return stars;
    }

    private int stars;
}
```

If the class is concrete, it _must_ implement all the methods of the interface, but if the class is abstract, it may leave any of them abstract.

An interface may extend another interface.

> :star: **Hint:** This will help you remember the terminology: A class extends a class, and an interface extends an interface, but a class _implements_ an interface.  
> 
> An interface may not extend or implement a class.

In exchange for these restrictions, interfaces provide one major capability: **multiple inheritance**.  While a class may extend _at most one_ other class, a class may implement many interfaces.  Further an interface my extend multiple interfaces.

```java
import java.net.URL;

public interface Download {
    public URL getDownloadURL();
}
```

```java
public class App extends Product implements Rateable, Download {
    // ...
   
    @Override
    public URL getDownloadURL() {
        // ...
    }
}
```

> :dart: **Exercise:** Extend your real estate model to cover not only properties for sale, but houses and apartments for rent as well.

## Anonymous classes

Java allows you to define a class _while instantiating it_.  Such a class can only be used once and has no name; thus, it's called an **anonymous class**.  

To define and instantiate an anonymous class, use `new` and a base class or interface name followed by the class body in curly braces.

For example, consider this interface:

```java
public interface Activity {
    void doIt();
}
```
and this method that uses it:

```java
public static void repeat(Activity activity, int count) {
    for (int i = 0; i < count; ++i)
        activity.doIt();
}
```

we can easily create an anonymous `Activity` instance that we use only to call `repeat()`:

```java
public static void main(String args[]) {
    repeat(new Activity() {
        public void doIt() { System.out.println("Hello, world!"); }
    }, 10);
}
```

> :dart: **Exercise:** Use <a href="http://docs.oracle.com/javase/7/docs/api/java/util/Timer.html"><code>java.util.Timer</code></a> and an anonymous instance of <a href="http://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html"><code>java.util.TimerTask</code></a> to write a program that prints "Hello, world!" once a second, indefinitely.


## Overloading

Java allows a single class to have multiple methods with the same name, as long as the numbers or types of their parameters are not the same.  This is called **overloading**.  For most purposes, overloaded functions are completely separate methods as far as Java is concerned.

```java
public final class Store {
    // ...
    
    public Product getProduct(long id) {
        for (Product product : products)
            if (product.getId() == id)
                return product;
        return null;
    }
    
    public Product getProduct(String name) {
        for (Product product : products)
            if (product.getName() == name)
                return product;
        return null;
    }
}
```

> :star: **Hint:** Use overloading judiciously. If two overloads differ substantially in what they accomplish, give them different names.

Unlike some languages, Java doesn't allow _default values_ for parameters; you must always provide a value to each of a methods parameters.  Overloading is sometimes used to work around this.

```java
class App {
    // ...
    
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdate() {
        setLastUpdate(new Date());
    }

    private Date lastUpdate;
}
```

Note that the overload that's missing the parameter value calls the overload that has the parameter; this reduces duplication of logic.

## Constructors

A **constructor** is a special method that specifies,

1. The parameters that are required to instantiate a class.
1. Code that is executed to initialize the class.

A constructor's name matches the name of its class.

```java
public class Album extends Product {
    Album(int id, String name, String artist, String genre) {
        super(id);
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    // ...
}
```

> :star: **Hint:** "Constructor" is often abbreviated "ctor".

A constructor with no parameters is calledthe **default constructor**.

If you specify no constructors, Java provides a default constructor for you.  This default constructor does nothing in its method body.

As we saw before, a subclass constructor calls its superclass constructor by calling the keyword `super`.  If the superclass does not have a default constructor, each subclass constructor _must_ do so.  If the superclass does have a default constructor, it is invoked automatically if no other constructor is.

Concstructors may also be overloaded.  One constructor may call another by calling `this` as if it were a function.  This must be the first thing the constructor does.  No other method may call `this`.

```java
Album(int id, String name, String artist) {
    this(id, name, artist, "unknown");
}
```

> :dart: **Exercise:** Write a class to model a maki roll.  In the constructor, specify the fish/vegetable, the filler, and the wrapper.  Provide overloaded constructors to specify "rice" as the default filler and "nori" as the default wrapper.  Then extend your class to accept multiple kinds of fish and vegetables, but provide an overloaded constructor that accepts a single type of fish as a convenience.

## Initializers

Code to run during instantiation doesn't have to go in constructors.  Instead, it can be placed in **initializer** blocks.  These appear simply as curly brace blocks within a class body.

```java
private List<Product> products;

{
    products = new ArrayList<Product>();
    products.add(new App(0, "Angry Birds", 3.99));
    products.add(new Album(1, "Rachet", "Shamir", "pop"));
}
```

The contents of an initializer block are consider part of _each_ constructor.

> :dart: **Exercise:** Figure out when initiazer block contents are executed relative to constructor bodies.  What if there are multiple initializer blocks?

<div></div>

> :star: **Hint:** Most Java developers don't use initializer blocks much.  However, they can be useful to separate out code that is common to all constructors that doesn't refer to constructor parameters.

## Static fields

Generally, the value of a field is specific to each instance of the class; _i.e._ each instance may have its own value of the field.  A field marked `static`, however, is not part of an instance, but rather of the class.  As such, it takes a single value no matter which instance it's used from.

```java
public abstract class Product {
    Product() {
        this.id = nextId;
        nextId++;
    }

    // ...

    private static long nextId = 0;
    private final long id;
}
```

## Constant fields

A field may be both `static` and `final`; this usually used to store a **constant**.  A constant field conventionally has an UPPERCASE\_WITH\_UNDERSCORES name.  

```java
public class Album extends Product {
    static final double STANDARD_PRICE = 8.99;

    @Override
    public double getPrice() {
        return STANDARD_PRICE;
    }
    
    // ...
}
```

> :star: **Hint:** As much as possible, place "special" values such as numbers and character strings in constant fields rather than directly into your code.  This prevents duplication and makes it easier to find and change the values in the future.

You can access a static field inside an instance of a class, through a instance, or directly from the class itself.

```java
Store store = new Store();
System.out.println(store.URL);
System.out.println(Store.URL);
```

> :star: **Hint:** A non-final static field should almost never be `public`.  If necessary, provide a static accessor method.

## Static methods

A method may also be marked `static`.  A **static method** executes independent of any instance of its class.  As such,

1. `this` is not available.
2. Non-static fields are not accessible.  However, static fields are.
3. Non-static methods may not be called.  However, other static methods may be.

> :dart: **Exercise:** Write a class called <code>Dice</code>.  Its constructor takes a number of dice, and it has a <code>roll()</code> that simulates rolling that many decide, and returns the total.  Also keep count of how many times the dice have been rolled, and provide a static accessor for this count.

The `main()` method must always be static.  That's because Java runs it before creating any instances of any classes.

> :star: **Hint:** Most programming languages allow you to define a function outside of a class; however, Java does not.  Many Java programmers group such functions together into a class or classes containing only static methods, which behave essentially as functions outside of a class.  There's no point in instantiating such a class, as all of its methods are directly accessible without an instance  The `Math` class is an example of this.

## Static initializers

An initialer block may be marked `static`, called a **static initializer**.  This is useful to perform complex initialization of static fields.

```java
public class Store {
    // ...
    
    public static final URL STORE_URL;

    static {
        String country = Locale.getDefault().getCountry();
        System.out.println(country);
        String url;
        if (country == "US")
            url = "http://play.google.com";
        else if (country == "UK")
            url = "http://play.google.co.uk";
        else
            throw new RuntimeException("no Play in " + country);

        try {
            STORE_URL = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("wonky Play URL");
        }
    }
}
```

A static initializer is run right before the first time a class is used in any way.

> :dart: **Exercise:** The <code>Math.sqrt()</code> method is relatively slow compared to many other math functions.  Write a class that precomputes the square roots of the numbers 0 through 100 in a static list.  Using this array, provide a <code>fastSqrt()</code> method that takes an integer and uses the list to provide a fast square root result.  If the paramter is not between 0 and 100, fall back to <code>Math.sqrt()</code>.

## Static and dynamic type

Every variable in Java has a **type**.  There are two kinds of types:

- There are eight **primitive types**: `boolean`, `byte`, `char`, `short`, `int`, `long`,  `float`, `double`.  Note that their names are all lower-case.

- All other types are **class types**, and refer to direct or indirect subclasses of `Object`, _i.e._ to class names.  Conventionally, their names are captialized.

The declared type of a variable is its **static type**.  In general, we use the word "static" to refer to anything that is known to the compiler.  Since a variable's type appears in the Java source code, the compiler is aware of it.

However, _in a running program_, the situation is more complicated.

- A variable of a primitive type may contain only a value of that type.  That's straightforward.

- A variable of a class type (an **object variable**) may refer to an instance of that class, _or_ to an instance of any direct or indirect subclass.  

That means at any point in a program, an object variable has _two_ types: the declared static type, and the type of the object it refers to.  This latter type is called its **dynamic type** or **runtime type**.  The dynamic type must always be either the static type or a direct or indirect subclass of the static type; Java makes sure this is _always_ true.

Here's an example of why a dynamic type can't be known to the compiler.

```java
public class Parent {}
public class Child extends Parent {} 

// ...

public Parent getInstance() {
    Scanner scanner = new Scanner(System.in);
    if (scanner.nextInt() == 0)
        return new Parent();
    else
        return new Child();
}

// ...

Parent obj = getInstance();
// Is it a Parent or a Child?
```

This property of object variables enables **polymorphism** in Java: a single variable can contain a variety of "shapes" (_i.e._ classes), as long as they are subclasses of the variable's static type.  

Similarly, a container can contain instances in a mixture of classes, as long as they are all instances of subclasses of the container's item type.  For example, a `List<Parent>` may contain instances of `Parent` and `Child` mixed together in the same list.

Since that `Object` is the superclass of _all_ other classes, a variable of type `Object` can refer to an instance of _any_ class.  It can't refer to a primitive type, though.  

To get around this, Java provides **boxed types**, which are eight object types corresponding to the eight primitive types.  For example, the boxed type for `long` is [`java.lang.Long`](http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html).  A boxed type is less efficient than the corresponding primitive type, but is a full-fledged class, so that object variables can refer to boxed instances.


## Dynamic dispatch

What if a subclass overrides a method from its superclass?

```java
public class Product {
    // ...

    public String getDisplayName() {
        return getName();
    }
}
```

```java
public class Album extends Product {
    // ...
    
    @Override
    public String getDisplayName() {
        return artist + " -- " + name;
    }
}
```

When a subclass overrides a superclass method, the overriding method will be used for each _instance_ of the subclass, _regardless of the type for which its called_.

```java
Product product = new Album("Prism", "Katy Perry", "pop");
System.out.println(product.getDisplayName());
```

This is called **dynamic dispatch**.  When it sees `getDisplayName()`, Java will dispatch to the version of this method based on the _dynamic type_ of the object, not the static type of the variable or expression.

> :star: **Hint:** This is the #1 most important concept in Java object oriented programming.  Almost all OO design patterns depend on this.
    

## Casts

Once a Java object has been instantiated, its dynamic type can _never_ be changed.  The only way to get around this is to create a new object of the desired type, and copy data from the old object to the new one.

An object can be referred to by variables of different types, though.  Since a variable may refer to an object of its static type or any of its subtypes, an object may be referred to by a variable of its own type _or a variable of any of its supertypes_, all the way up to `Object`.

These are all fine:

```java
Child c = new Child();
Parent p = new Child();
Object o = new Child();
```

In general, a value of an object type may be used wherever a value of any of its superclasses is called for.  This is free and always safe.  It's called **casting up** or a **widening cast**.

The converse is not necessarily safe, and not free.  Consider this example:

```java
Parent p = new Child();

// ... other stuff happens ...

Child c = p;  // Not allowed!
```

Is the last assignment OK?  Remmeber that a variable of type `Child` can refer to an object of type `Child` or any of its _subclasses_.  But all the compiler knows for sure is that the object referred to by `p` is of type `Parent` or one of its subclasses.  Somewhere along the way, `p` may have been reassigned to an object of type `Parent`, which cannot be stored in `c`.  As a result, Java does not allow this assignment.

To get around this, you must explicitly **cast** the variable.  

```java
Child c = (Child) p;
```

A cast is a _claim_ you make to the Java compiler that even though `p` is of _static_ type `Parent`, its _dynamic_ type is actually `Child` (or one of its subclasses).  Java doesn't trust you, though, and will check _at runtime_ whether the cast is valid.  If it's invalid, Java throws a `ClassCastException` at that point.

> :dart: **Exercise:** Write a program that throws a `ClassCastException`.


## instanceof

Since a cast throws an exception if the dynamic type of the value doesn't conform to the cast type, we have a way of testing _at run time_ the type of an object.

```java
Object val = ...;
boolean isInstanceOfTestClass;
try {
    TestClass unused = (TestClass) val;
    isInstanceOfTestClass = true;
}
catch (ClassCastException exc) {
    isInstanceOfTestClass = false;
}
```

This is rather inefficient, not to mention cumbersom.  Java provides an easier way: the **instanceof** operator.

```java
boolean isInstanceOfTestClass = val instanceof TestClass;
```

> :star: **Hint:** It's bad form to use `instanceof` much.  Whenever you find yourself using it, ask yourself whether there's another mechanism you could use instead; it would probably lead to better code.

##Exit Ticket  
Please complete the exit ticket [here](https://docs.google.com/forms/d/1J9B9Y7BX7xdHYzUfLeoLWAS7Ff6zDCBsM5puqZtT4aM/viewform?usp=send_form). 

