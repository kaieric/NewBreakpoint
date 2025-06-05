# NewBreakpoint
Building on top of existing class project for fun, mostly completely refactoring old code and adding new features.
Also for you Samara, I decided to keep the incorrect title. 


GIT HUB:

For some pushes we can just use the push button?
Get some standardized git instructions

When adding a new local repository (basically a workspace):
-Make the remote repository on github.com
-Use vscode to make a clone of this (alternatively "git init" turns the file your cd'd in into a repo)
-Make sure you are in the correct folder (the local repo will be your workspace)
-"git status" tells you if you are indeed within a repo

When adding a new remote Repository (you should be in your local cloned repo to do this):
-"git remote add origin" https://github.com/your-username/your-repo-name.git
-"git remote -v"



Terminal instructions:
1. "git add" . OR "git add filename1 filename2"
2. "git commit -m"MESSAGE HERE""    >> "git log --oneline" This shows a short list of commits for verification.
3. "git push"
4. just in case if 3 doesn't work use instead (replacing step 3 while retrying): "git push -u origin main"

Step 4's purpose is to set main as the default push location.

When Pulling code:
just write "git pull" and you're done.

Links:
https://code.visualstudio.com/docs/sourcecontrol/intro-to-git ()
parse hour: https://stackoverflow.com/questions/22278748/zsh-parse-error-near-n-when-adding-aws-keys-as-environment-variables
adding clone repo with vscode: https://stackoverflow.com/questions/46877667/how-to-add-a-new-project-to-github-using-vs-code


Indentation: On the bottomost bar, selects Spaces: [number]. At the top search bar, select indent by tab, and select the number of spaces per tab. 4 spaces is default, select it.




PRELIMINARY GOAL:
Basic functionality of:
-Menu
-Healthbar
-Cutesy pricks, ball, and paddle (skins atop the collision matrices)
-Rename point[] shape to matrix, and have a collision matrix, and then aesthetic matrices.

-Alter Polygon such that instead of a single shape, we have a collision matrix IN ADDITION to an array of skin arrays / images??? which are all moved by getPosition and whatnot.
-
-

NOTES:
-Spawn ball should have some parameters, allowing spawning them in in motion or not, with a starting angle. This will be used to handle both respawning and cloninng
-In motion should be a ball variable, with a ball array being looped through, each ball's inmotion checked before moving and checking them.
-Ball death triggered when the ball array is empty.
-When a ball is cloned through a brick, the newly spawned ball should start off moving in the opposite direction of the original ball, in order to stop any issues,
-Make sure that the ball moves as fast as the paddle, or there will be problems with side collisions.


-Alternating bricks, each time a brick is hit, one set of bricks goes low opacity and doesn't have collision, and the other group reappears.


The below mark failed attempts.
test 01
test 02
test 03