Entanglement Polytopes Library

This library can be used to calculate entanglement polytopes for distinguishable
particles. See http://entanglement-polytopes.org/

There are also Scala algorithms to calculate Schubert polynomials, rectangular
tableau and shuffle permutations.

QUICKSTART

1. Download the entire repository.

2. Open Eclipse (the Scala plugins must be installed).

3. Create a run configuration which passes command line arguments "2 2". This
   indicates the program should calculate the 2x2x4 polytope.

4. Run the application.

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
   
Native Libraries:

-- COIN Branch and Cut (CBC) Solver for mixed integer programming (MIP)
   (http://www.coin-or.org/projects/Cbc.xml)
   
(A previous version used the GLPK integer programming library, but it is not
thread-safe)