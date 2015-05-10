_All notable changes to the [Numerus project](http://matjaz.it/numerus/) 
will be documented in this file._

***************

v0.5.0
======
Codename: **Quinque**  
Build day: 69

Added
-----
- `RomanInteger` is now `Serializable`

Changed
-------
- Default `RomanNumeral` now stores `NULLA` as numeral, meaning _zero_.
  Default `RomanInteger` also stores (`NULLA`, 0).
- Optimized integer to roman conversion
- Set regex fields in RomanNumeral and converter fields in RomanInteger
  static for memory optimization
- This changelog format

Removed
-------
- Package `core`: all contained classes moved to `it.matjaz.numerus`

Fixed
-----
- Missing trailing slash `/` in links to project web page in all 
  sources and removed `www` from urls

v0.4.0
======
Codename: **Quattuor**  
Build day: 52

Added
-----
- `RomanInteger`: a container of paired `RomanNumeral` and its `int` 
   value. Extends `Number`, is `Comparable` and `Cloneable`.
- `RomanNumeral` is now `Cloneable`, `Serializable` and `CharSequence`.

Removed
-------
- Conversions passing `Strings` are not possible anymore. Only with
  `RomanNumerals` to ensure syntax correcteness

Fixed
-----
- Various JavaDoc fixes


v0.3.1
======
Codename: **Tres Uncia**  
Build day: 51

Added
-----
- This changelog file
- Library logo in `.svg` and `.png`
- Much much more [Trello](https://trello.com/b/WtjZ94R3/numerus) 
  documentation, board background and picures

Fixed
-----
- Missing or error-containing license headers in some files


v0.3.0
======
Codename: **Tres**  
Build day: 41

Added
-----
- Mozilla Public License v2.0 to project and files
- Readme file
- Container of syntactically correct roman numerals `RomanNumeral`
- Jar-with-dependencies build
- Project info in `pom.xml` and `manifest`


v0.2.0
======
Codename: **Duo**  
Build day: 39

Added
-----
- Bidirectional conversions roman numeral <-> arabic integer, performed 
  by `RomanConverter`


v0.1.0
======
Codename: **Unus**  
Build day: 38

Added
-----
- Project initialization to Java 1.8, Maven, JUnit. Project id: 
  `it.matjaz.numerus`
- Structures of pairs (basic roman character, its `int` value) to be 
  used for conversion, generated by `RomanCharMapFactory`