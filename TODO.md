# TODO List:
This is a TODO list for the Implementation section of the project, _Heslington Hustle_.
## UI/Screens
- ~~Add hours left in game~~
- ~~Game intro~~
- ~~End of day~~
- ~~Next day~~
- **Pause Game**
  - Should exit or resume
- **End Game (score screen)**
- How to play (Matt)
- Character selection
  - Display characters on screen rather than buttons? (maybe for refactoring)
## System
  - ~~Energy~~
  - ~~Time~~
    - Show how many hours left
    - Or time in general
    - **7:30 AM + 16 hours = 23:30**
  - Hunger
    - Don't need to show
    - ~~Counter~~
- Score
  - Don't need to worry till next assessment
## Classes
- ~~Activity (merge into one)~~
- ~~Stats (Matt did?)~~

## Activities
- **Decide activities**
- Need to work out how much energy/score each activity uses
  - Should be enough to do 3-5 each day
  - It is the first assessment, so it's not going to be the full game time as we only have 1 activity each
  - Don't need to determine score for assessment 1
- Game conditions
  - ~~Sleep ends day~~
  - **_Don't need to do more than 16 hours, project brief says "each day contains 16 hours", hard set_**
  - Attempt to go past more, force next day ("You are tired. Sleep")
  - **Only interaction after 16 hours is to sleep**
- Counters
  - ~~Activities~~
  - How many meals a day
  - Min points for game completions
## Refactoring
- ~~Make one class for all activities (the others don't actually alter anything)~~
- ~~Neaten ActivityScreen code (remove dupes)~~
- Try to reduce making so many screens
  - **Screen manager?**
  - Don't call _new_ screens
- Alter collision detection (something wrong with moving down and left when right and left moving top right corner)