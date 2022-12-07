# UWU-Interpreter
A trash Assembly-like, "uwu" based interpreted language.

Commands take the form of: `command [subcommand] [args...]`

## Command List
- `awway <subcommand>` Used for handling arrays.
  - `cweate <var holding size> <var to put array in>` Creates an array.
  - `gewt <array var> <index var> <dest var>` Retrieves a value from `array var` at `index var` and stores it in `dest var`.
  - `pwut <src var> <array var> <index var>` Puts `src var` into `array var` at `index var`.
- `fwile <subcommand>` Used for file operations.
  - `cwose <var holding file ref>` Closes a file stream.
  - `nwext <var holding file ref> <variable to put value in>` Reads the next token from the file into the variable.
  - `opwen <var holding file path> <var to put file ref in>` Opens a file and prepares it for reading.
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
