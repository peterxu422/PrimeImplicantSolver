PrimeImplicantSolver
====================

Solves for Prime Implicants given the number of input variables and minterms

Inspiration: For my Advanced Logic Design class, I was frustrated with solving for the prime implicants of a truth table by hand. I became even more frustrated when I could not find a solver online. So I made one in Java. Feel free to use the code. Please credit me if you do.

Here is an example of how the program works:

Program inputs: <num input vars> <minterm list>

num input vars: Integer for the number of input variables in the truth table

minterm list: The minterms in the truth table of interest

Example:

<pre><code>
java -jar PIGenerator.jar 4 "0 2 3 4 5 6 7 8 9 10 11 12 13"


Column 1 

  (0) 0000 x
  (2) 0010 x
  (4) 0100 x
  (8) 1000 x
  (3) 0011 x
  (5) 0101 x
  (6) 0110 x
  (9) 1001 x
 (10) 1010 x
 (12) 1100 x
  (7) 0111 x
 (11) 1011 x
 (13) 1101 x


Column 2 

   (0,2) 00x0 x
   (0,4) 0x00 x 
   (0,8) x000 x 
   (2,3) 001x x 
   (2,6) 0x10 x 
  (2,10) x010 x  
   (4,5) 010x x 
   (4,6) 01x0 x
  (4,12) x100 x
   (8,9) 100x x
  (8,10) 10x0 x
  (8,12) 1x00 x
   (3,7) 0x11 x
  (3,11) x011 x
   (5,7) 01x1 x
  (5,13) x101 x
   (6,7) 011x x
  (9,11) 10x1 x
  (9,13) 1x01 x
 (10,11) 101x x
 (12,13) 110x x


Column 3 

     (0,2,4,6) 0xx0 _
    (0,2,8,10) x0x0 _
    (0,4,8,12) xx00 _
     (2,3,6,7) 0x1x _
   (2,3,10,11) x01x _
     (4,5,6,7) 01xx _
   (4,5,12,13) x10x _
   (8,9,10,11) 10xx _
   (8,9,12,13) 1x0x _

Prime Implicants
A'D'  B'D'  C'D'  A'C  B'C  A'B  BC'  AB'  AC'  
</code></pre>
