# UWU-Interpreter
A trash Assembly-like, "uwu" based interpreted language.

Commands take the form of: `command [subcommand] [args...]`
You can have comment lines by starting the line with `(^o^)`.
You can create label lines (used by `gowtu`, `subwutine`, and `wumpif`) by starting the line with `!`.

Look at `Example.uwu` for examples of all the features and commands.

## Command List
- `awway <subcommand>` Used for handling arrays.
  - `cweate <size var> <array var>` Creates an array of size `size var` in `array var`.
  - `gewt <array var> <index var> <dest var>` Retrieves a value from `array var` at `index var` and stores it in `dest var`.
  - `pwut <src var> <array var> <index var>` Puts `src var` into `array var` at `index var`.
- `fwile <subcommand>` Used for file operations.
  - `cwose <file ref var>` Closes file stream.
  - `nwext <file ref var> <dest var>` Reads the next token from the file into `dest var`.
  - `opwen <file path var> <file ref var>` Opens a file and stores stream reference in `file ref var`.
- `gowtu <label>` Transfers execution to target label.
- `inpwut <var>` Reads a line from standard input and puts it into `var`.
- `owp <subcommand>` Used for handling operations on variables.
  - `awpend <src var> <dest var>` Appends `src` onto the end of `dest`.
  - `chrawrAt <str var> <index var> <dest var>` Retrieves the character in `str var` at `index var` and places it in `dest var`.
  - `cowpy <src var> <dest var>` Copies value in `src var` and overwrites `dest var`.
  - `dwiv <src var> <dest var>` Divides `src var` by `dest var` and places the result in `dest var`.
  - `muwlt <src var> <dest var>` Multiplies `src var` by `dest var` and places the result in `dest var`.
  - `pwus <src var> <dest var>` Adds `src var` to `dest var` and places the result in `dest var`.
  - `swub <src var> <dest var>` Subtracts `src var` by `dest var` and places the result in `dest var`.
- `pwint <var>` Outputs `var` to standard out.
- `subwutine <label>` Moves execution to label with it's own local variable scope.
- `vwar <subcommand>` Handles variable creation.
  - `gwobaw <var> <value>` Creates global variable `var` and places `value` in it. `value` can contain spaces.
  - `wocaw <var> <value>` Creates a local variable `var` and places `value` in it. This is only effective if you use `subwutine`s.
- `wetwurn` Returns execution to the last place calling `subwutine`.
- `wumpif <subcommand>` Jumps to other lables on a condition.
  - `eqwal <var a> <var b> <label>` Jumps to `label` if `var a = var b`.
  - `gw8r <var a> <var b> <label>` Jumps to `label` if `var a > var b`.
  - `gw8rOrEqwal <var a> <var b> <label>` Jumps to `label` if `var a >= var b`.
  - `notEqwal <var a> <var b> <label>` Jumps to `label` if `var a != var b`.
