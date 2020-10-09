# Eaxam-Scheduler-Using-Graph-Coloring-and-Kempe-Chain
SOLVING EXAMINATION
TIMETABLINGTORONTO DATASET
 A set of 13 real-world exam timetabling problems
http://www.cs.nott.ac.uk/~pszrq/data.htm
http://www.asap.cs.nott.ac.uk/external/resources/
 For this assignment, we use five benchmark cases:

 CAR91, CAR92, KFU93, TRE92, YOR83DESCRIPTION OF A CASE: YOR83 (181 COURSES, 941 STUDENTS)

 yor-f-83.crs (input: course file), yor-f-83.stu (input: student file)

 yor83.sol (output: solution file)

 A part of yor-f-83.crs (input: course file)

0001 23 (COURSE 0001 HAS 23 STUDENTS)
0002 19 (COURSE 0002 HAS 19 STUDENTS)
0003 67
… … …
0180 22
0181 22DESCRIPTION OF A CASE: YOR83 (181 COURSES, 941 STUDENTS)
A part of yor-f-83.stu (input: student file)
0031 0059 0091 0110 0133 0153 0166 0174 (COURSES OF STUDENT 0001)
0007 0024 0031 0060 0079 0110 0130 0152 (COURSES OF STUDENT 0002)
… … …
0040 0053 0074 0101 0103 0118 0179 (COURSES OF STUDENT 0939)
0073 0074 0088 (COURSES OF STUDENT 0940)
0043 0098 0100 0102 0151 0158 (COURSES OF STUDENT 0941)DESCRIPTION OF A CASE: YOR83 (181 COURSES, 941 STUDENTS)
 A part of yor83.sol (output: solution file)
 The solution has 21 timeslots.
11 12 (COURSE 0011 is assigned to TIMESLOT 12)
43 18 (COURSE 0043 is assigned to TIMESLOT 18)
118 0
114 0
37 20
… … …HOW TO SOLVE?
 Constructive Heuristics
 Used for building a feasible solution
 Can be viewed as providing an ordering of nodes
 Largest degree, Saturation degree, Largest enrollment, Largest weighted degree,
Random ordering
 Perturbative Heuristics
 Used for finding an improved solution in the vicinity or proximity of the current solution
 Kempe-chain Interchange
 Pair-swap Operator
 SWO
 a general approach to optimization: `Squeaky Wheel' Optimization
 Construct/Analyze/Prioritize cycle
 can be viewed as operating on two search spaces: solutions and prioritizations.
 coupled searchHOW TO SOLVE? (CONSTRUCTIVE HEURISTICS)
 Largest degree
 The node with the largest number of edges (conflicting
examinations) is scheduled first.
 Saturation degree
 The well-known Brelaz heuristic (used in DSatur algorithm)
 provides dynamic variable (or node) ordering
gives priority to the node with the least colour available.
 Largest enrollment
 The largest number of students registered for the examinations
is scheduled first.
 Largest weighted degree
The examination with the largest number of students whoare
involved in the conflict is scheduled first.
 Random orderingHOW TO SOLVE? (PERTURBATIVE HEURISTICS)
 Kempe-chain Interchange
 A Kempe chain is defined as a connected subgraph that contains v,
and that only comprises vertices coloured with colours i and j.
 Kempe (v, c(v), j) or Kempe (v, i, j)
 take a particular Kempe chain and swap the colours of all vertices
contained within it.
 Pair swap Operator
 a pair-swap is the simultaneous application of two Kempe-chain
interchanges applied to Kempe (v,c(v),c(u)) and Kempe (u,c(u),c(v)).
 Let the Kempe chains KEMPE (u,i, j) and KEMPE (v, j,i) both contain
just one vertex each (therefore implying that u and v are
nonadjacent.) A pair swap involves swapping the colours of u and v.HOW TO SOLVE? (SWO)
 The components of SWO
 The constructor: A greedy algorithm is invoked.
 The analyzer:
 Find the trouble spots, i.e., those elements, that, if improved, are likely to improve
the objective function score.
 Identify the so-called “trouble makers” and assign blame values to them.
 The prioritizer:
 Use the results of the analysis to generate new priorities that determine the order in
which the greedy algorithm constructs the next solution.
 The nodes with higher blame values will be moved forward in the priority sequence
while the ones with small blame values will remain in the back of the sequence.OUTPUT
Benchmark Data Toronto Results
(known best solution)
My result
(my scheme-1)
Timeslots Penalty Timeslots Penalty
CAR91 35 4.42
CAR92 32 3.74
KFU93 20 12.96
TRE92 23 7.75
YOR83 21 34.84
