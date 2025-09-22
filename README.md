CF VSCode Java — Minimal README

Tiny, practical reference so you can start coding & testing fast.

Layout (open workspace root)
CF/
├─ .vscode/              ← tasks + launch + settings
├─ templates/Main.java   ← paste this for new problems
├─ newProblem.bat        ← create new problem folder (runs in workspace root)
└─ problems/
   └─ <ProblemName>/
       ├─ src/
       │  └─ Main.java
       ├─ input.txt
       ├─ output.txt
       └─ run.bat

Quick commands (what to press)

Build: Ctrl+Shift+B

Run / Debug: F5 (select Debug (Launch) - Main)

Single-key build+run (terminal): Ctrl+Shift+R

Build+run → save to file: Ctrl+Alt+R

Double-click run.bat in a problem folder to compile & run from Explorer.

How to add a problem

From workspace root:

newProblem.bat 189A-CutRibbon


This creates problems\189A-CutRibbon\src\Main.java, input.txt, output.txt, and run.bat.

File locations & input

Put sample input in problems\<NAME>\input.txt (task/launch expects ..\input.txt because the build runs from src).

templates/Main.java already reads input.txt if present; otherwise it uses System.in (safe to paste to Codeforces).

Manual compile/run (if needed)

From problems\<NAME>\src:

if not exist ..\out mkdir ..\out
javac -d ..\out *.java
java -cp ..\out Main < ..\input.txt

Debug / Java version notes

If you see UnsupportedClassVersionError, either:

Recompile targeting your runtime: javac --release 21 -d ..\out *.java (adjust version to match your java), or

Point VS Code to the matching JDK by setting java.home in .vscode/settings.json and restart VS Code.

Keep Main.java without a package line so Codeforces accepts it.

Quick fixes

No out visible? .vscode/settings.json hides it. Remove the files.exclude rule or open ..\out in Explorer.

Build errors? Save file, open src\Main.java (active file), then Ctrl+Shift+B to see compiler output.