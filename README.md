Polytope

This library can be used to calculate generic (as in "full") entanglement polytopes for 
distinguishable particles. These arise in the study of quantum computing.
For example, see the largest polytopes at http://entanglement-polytopes.org/

This library also includes algorithms for calculating Schubert polynomials, 
rectangular tableaux and shuffle permutations.

PLEASE NOTE: This library only works on linux at the moment, because it depends 
on an operations research library compiled in native (Linux) code. It can 
easily be modified to be purely Java if you know a good Java library for 
doing Mixed Integer Programming (MIP).

LICENSE

I would release it under MIT, but I distribute it to work out of the box using a GPL library (Parma), so it is GPL.

QUICKSTART USAGE

1. Download the entire repository and unzip it.

2. Open a console window and run the command:
     $ ./polytope --help
   This will display all the command line arguments.

QUICKSTART DEVELOPMENT

1. Clone the entire repository onto a local machine.

2. Open Eclipse (the Scala plugins must be installed!)

3. Import the existing project into the workspace.

4. Run the application using the configuration:
     Scala Application => Polytope
   The command line arguments can be seen by passing the argument "--help" 
     Example arguments are "pure -iv 2 2 2" which computes inequalities and
     vertices of the polytope of pure 2x2x2 distinguishable states.


REQUIREMENTS

Linux 32/64-bit System

TODO

-- Migrate to sbt and remove included libraries


INCLUDED LIBRARIES

All necessary libraries are included. As far as native libraries, the master 
branch currently links to 32-bit Linux libraries (which of course also run on 
64-bit Linux).

Java Libraries:

-- Parma polyhedral library for calculating edges of a polyhedral cone 
   (http://bugseng.com/products/ppl) (GPLv3 License)
   
-- Google Operations Research (OR) Tools which provide a java interface to
   the CBC library
   (https://code.google.com/p/or-tools/) (Apache 2.0 License)
   
-- Scallop for processing command line arguments
   (https://github.com/scallop/scallop) (MIT License)

-- ScalaTest 2.1.0 for writing tests of the code
   (https://github.com/scalatest/scalatest) (Apache 2.0 License)
   
Native Libraries:

-- COIN Branch and Cut (CBC) Solver for mixed integer programming (MIP)
   (http://www.coin-or.org/projects/Cbc.xml) (Eclipse Public License 1.0)
   
(A previous version used the GLPK integer programming library, but it is not
thread-safe.)
