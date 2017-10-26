# Interval
Java Interval Arithmetic Library


The beginning of this library lies in the inexactness of native data types. Though 64 bits are quite a lot and the 'double' from IEEE745 for floating point arithmetic does a good job, is still is limited in its precision. As an improvement to the basic types, Java comes with the expansion types 'BigInteger' and 'BigDecimal' for integer and floating point numbers. But a big problem stays the same: How to handle periodic numbers.
At this point I had the idea to create another data type, building on top of BigInteger and improving BigDecimal by building rational numbers. Because their only problems are non-periodic infinite decimals, I called them 'ExactDecimal'. Consisting of two BigIntegers as numerator and denominator, one can perform all basic mathematical operations (such as addition, subtraction, mulitplication and division) without loosing any precision.
But working on BigDecimal yielded to the intent of creating a library of different functions and special operations for this type (like java.lang.Math). But problems rise again: Calculating the square root is not a big deal, when it comes to the algorithm. On the other hand it interferes with the base concept of exactness when it comes to values like sqrt(2).
I realised, another data type is needed to store uncertanty and allow keeping track of it. This is where the concept of interval arithmetic comes in: Instead of numbers, intervals are used to describe a region where the value could lie in. The following examples may help understanding what this means.

Pi is approximately 3.141592653589793...
Storing the value with a precision of 10 decimals results in the interval \[3.14159265358, 3.14159265359\].
A less precise representation of 2 decimals would be \[3.141, 3.142\].
The process of creating interval numbers is to cut of the digits at some point and creating a lower and upper bound from it that frame the exact value.
