# TODO List:
This is a TODO list for the Implementation section of the project, _Heslington Hustle_.
## UI/Screens
  - Game intro
  - End of day
  - Next day
  - **Pause Game**
    - Should exit or resume
  - **End Game (score screen)**
  - How to play (Matt)
  - Character selection
    - Display characters on screen rather than buttons? (maybe for refactoring)
## System
  - Energy
  - Time
    - Show how many hours left
  - Hunger
    - Don't need to show
    - Counter
- Score
  - Don't need to worry till next assessment
## Classes
- Activity (merge into one)
- Stats (Matt did?)

## Activities
- **Decide activities**
- Need to work out how much energy/score each activity uses
  - Should be enough to do 3-5 each day
- Game conditions
  - Sleep ends day
  - Go past 16 hours, less energy next day
  - Go past more, force next day ("You are tired. Sleep")
  - Only interaction after 16 hours is to sleep
  - **If the player doesn't study for two days in a row, END GAME**
- Counters
  - Activities
  - How many meals a day
  - Min points for game completions
## Refactoring
- Make one class for all activities (the others don't actually alter anything)
- Neaten ActivityScreen code (remove dupes)
- Try to reduce making so many screens
  - Screen manager?
  - Don't call _new_ screens