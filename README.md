# Debug Tutorial

We're going to do some basic debugging to find a logic problem or two in some Java code.  First, a description of the code - you have two .java classes to work with:

* `Fraction.java` - used to represent integer fractions. We can create, add, subtract, and get the decimal value of a fraction.
* `FracTest.java` - contains a `main()` method which we use to test our Fraction objects.

Take a look at the code to familiarize yourself with it, and run the `main()` method. You should see some problems right away - you might even know how to solve them simply by looking at the code. However, we will use the debugger to show you how to diagnose the problems you may see.

## Debuggers 101

To date, our debugging has been limited to adding `.println()` statements to see what values are being assigned or changed at different points. This is of limited effectiveness, however:
* Simple `.print()` and `.println()` statements don't tell us where in a method we are.
* We can't see what other methods may have called us.
* We can't change values to see how they affect code execution.
* We can't pause execution when a certain condition is met.

Debuggers allow us to do this, and much more.  But first, here are some keywords of which to be aware:

* **Breakpoint**. A defined point in the code where we want to pause execution temporarily. Breakpoints are usually indicated by a small dot in the margin of the editor.
* **Execution point**. Which line of code will be executed next. Indicated usually by highlighting that line. 
* **Step**: After we hit a breakpoint, we can execute code systematically by *stepping through* it. There are three kinds of step actions:
  * **Step Into**. Follows execution *into* any method calls.
  * **Step Over**. Follow execution, but skips *over* method calls - execution pauses after the call returns.
  * **Step Out**. Executes non-stop until we exit the current method.
* **Resume**: Starts running the code normally again.
* **Watch variables/expressions**: Any variable or expression who's value we want to view over time.
* **Call stack**: A visual representation of the chain of method calls that leads to the current execution point.

With those concepts in mind, let's start the debugger.

## Starting the debugger
 
Every IDE has a different way of starting a debugger.  For Eclipse, you can do this two main ways:
* Hit the F11 key.
* From the menu, select Run, Debug.

So, start Eclipse, import the DebugPractice project from GitHub, and follow these steps:

1. Open the DebugPractice project, then open FracTest.java.
2. Hit F11 to start the debugger.

You should see the following output:

```text
Before
======
First : 2/3, value: 0.0
Second: 3/4, value: 0.0

After
=====
First : 5/7, value: 0.0
Second: 3/4, value: 0.0
```

Nothing really unusual happened - wasn't this supposed to be a debug exercise?

The problem is that nothing bad happened, so the debugger had no reason to stop. Let's get the debugger involved by setting a breakpoint.

3. Highlight line 4 (reads `Fraction first = new Fraction(2,3)`)
4. Hit CTRL-SHIFT-B to toggle a breakpoint.

You should see a small blue dot appear in the left margin of the Eclipse editor. You can also double-click in this area to set the breakpoint.

5. Now hit F11 to restart the debugger.

You should be dropped into the Debug perspective in Eclipse, with line 4 highlighted.

NOTE: You can always get back into the regular Java perspective by clicking the Java icon next to the Debug icon in the upper right corner of the display. You can also select Window, Perspective, Open Perspective, Java Browsing to return to the regular window layout.

## Stepping

Let's practice stepping through this code.

1. In the source code window, hit F5 to step into the `Fraction` constructor.

Notice how the call stack in the **Debug** view changed. Also notice how the source code view changed to the `Fraction.java` file, with the first line of the constructor highlighted.

2. Switch the source code view back to the `FracTest.java` file.

Notice that the breakpoint line is still highlighted. You can also click on the `FracTest.main(String[])` line in the **Debug** view.
   
3. Switch back to `Fraction.java`, and hit F5 again.

The execution point jumps to the `if` statement. Take a look at the **Variables** view - you should see three items there: `this`, `num`, and `den`. These represent the current object, and the two parameters to the constructor. You can expand the `this` variable to see the instance variables it contains.

4. Hit F5 again.

Now, we're at line 21. What happened? Well, we evaluated the condition in the `if` statement, and it was `false`, so the next line to execute was this one.

5. Expand the `this` variable (if you haven't already) and hit F5 again.

Now we're at line 22, but noticed that `this.num` is now highlighted in yellow in the **Variables** view. That's because whatever just executed changed that value.  Of course, the line that executed was `this.num = num`, so that's expected, but imagine if you saw something change unexpectedly. Could that be helpful?

6. Hit F5 again.

Now `this.den` changed, right? And the next line to be executed is... the closing brace? Why? Because that's where the method, by default, returns.

Also notice that when you highlight `this`, the value printed is `'2/3'`. That's because Eclipse is evaluating `this.toString()` to show the value. If you don't have a `toString()` method in your class, then objects would just show their addresses.

7. Hit F5 once more to return to the constructor line in `FracTest.java`.

Now, step through the next constructor. At each point, take a look at what's changing, what you can look at, and what you can do with that information. Stop when you get to the first `println()` in `FracTest.java`.

## Watch Variables
At this point, we have two objects created and visible in the **Variables** view, `first` and `second`. We can inspect those variables, or even change their values if we want.

1. Expand the `first` object in the **Variables** view.

You will see the fields `den` and `num` listed there. Let's change one of them to see what happens. The change a value, just click on the value and type a new value.

2. Change the `num` field to some value other than 2.

Notice that the value is highlighted in yellow again - that's because it's value changed.  If you click on the `first` entry, or hover it in the `FracTest.java` view, you'll see the value reported there also changed.

What happens if you set the value to a `double`?  Or a `String`?

3. Step through the code twice to land on line 8, then step once more.

You should be in the `first.toString()` method now. What happens if we change something here?

4. Change `den` to something other than 3 (but not 0).
5. Hit F7 to step out of the method.
6. Check the value of `first` in the **Variables** view.

What happened and why? Remember that while Java parameters cannot be changed in a method call, objects are actually references to memory locations. We cannot change the actual reference, but we can change the data to which it refers.

7. Hit F6 to step over the next `println()` statement. You should be at line 12 now.

My output looks like this:

```text
Before
======
First : 5/7, value: 0.0
Second: 3/4, value: 0.0
```

Right now, you should see a problem with this output. We print the object (which uses `.toString()`), then the value (which uses `.value()`). However, the value printed doesn't appear right - it's always a whole number (0.0, 1.0, etc). Shouldn't the value of 3/4 be 0.75? Let's do some more exploration to figure out what might be happening.

## Watch Expressions

We don't just have to view the values of variables - we can also view the values of any valid Java expression as well. We do this using the **Expressions** view. (If you don't see this view next to **Variables**, select Window, Show View, Expressions to see it.)

Let's add a new expression to watch.

1. Click _Add new expression_, then type the following: `second.value()`

Notice that 0.0 is listed as the value, which is what we see from the `println()` as well. But how does it get that value? Let's look at the code:

```Java
public double value() {
 	return num/den;
}
```

So we're calculating `num/den` as we should be, but let's be certain.

2. Add another expression: `second.num/second.den`

Whoops! We get an error - why? Because both `num` and `den` are `private` variables.  We'll have the use the getters.

3. Change the expression so we get an answer.

OK, so that's interesting - the expression evaluates to 0. Not 0.0. Which makes sense