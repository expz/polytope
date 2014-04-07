Entanglement Polytopes Library

This library can be used to calculate entanglement polytopes for distinguishable
particles. See http://entanglement-polytopes.org/

There are also Scala algorithms to calculate Schubert polynomials, rectangular
tableaux and shuffle permutations.


QUICKSTART

1. Download the entire repository.

2. Open Eclipse (the Scala plugins must be installed).

3. Import the existing project into the workspace.

4. Run the application using the configuration:
     Scala Application => Polytope
   The command line arguments specify the size of the polytope to compute, 
     e.g., "2 2" computes a 2x2x4 polytope. 


REQUIREMENTS

Linux 32/64-bit System


INCLUDED LIBRARIES

All necessary libraries are included. As far as native libraries, the master 
branch currently links to 32-bit Linux libraries (which of course also run on 
64-bit Linux).

Java Libraries:

-- Polco library for calculating edges of a polyhedral cone 
   (http://www.csb.ethz.ch/tools/polco)
   
-- Google Operations Research (OR) Tools which provide a java interface to
   the CBC library
   (https://code.google.com/p/or-tools/)
   
-- ScalaTest 2.1.0 for writing tests of the code.
   
Native Libraries:

-- COIN Branch and Cut (CBC) Solver for mixed integer programming (MIP)
   (http://www.coin-or.org/projects/Cbc.xml)
   
(A previous version used the GLPK integer programming library, but it is not
thread-safe.)