_All notable changes to the jNumerus project will be documented in this file._

***************
- Rename full project from "Numerus" to "jNumerus". The name "Numerus" indicates
  the name of [the C port](https://github.com/TheMatjaz/Numerus) of the project 
  with many enhancements.
- Project discontinued
- Trello board discontinued

v0.7.0
======
Codename: **Septem**  
Build day: 189

Added
-----
- Command line interface `RomanRepl` for interactive conversions along with 
  project information and roman syntax help.
- Conversion of `java -jar jNumerus-*.jar` argument

Changed
-------
- Ready for internationalization: hardcoded strings are moved to a 
  `ResourceBundle` file


v0.6.0
======
Codename: **Sex**  
Build day: 185

> Note: the codename is not a joke, it means "six" in latin.

Added
-----
- `RomanException` for generic exceptions in the jNumerus package.
- `IllegalNumeralSyntaxException` for strings with wrong roman syntax when they
   are inserted into `RomanNumeral` objects.
- `IllegalArabicValueException` for conversion of integers that are not in the 
   suitable range of [0, 3999], as with the standard syntax.
-  Package info JavaDoc.

Changed
-------
- `NULLA` is now defined as a static final variable in `RomanNumeral`.
-  Cleanup of this changelog

Git repository changes
----------------------
- Changed GitHub username from `MatjazDev` to `TheMatjaz` making the URL of the
  jNumerus repository become <https://github.com/TheMatjaz/jNumerus> - there are
  **no redirects** from the old URL
- Removed all feature git branches: from now on they will have short 
  lives only until the completion of the feature
- Removed `.gitignore` file


v0.5.0
======
Codename: **Quinque**  
Build day: 69

Added
-----
- `RomanInteger` is now `Serializable`

Changed
-------
- Default `RomanNumeral` now stores `NULLA` as numeral, meaning _zero_,
  instead of an empty string.
- As a consequence, default `RomanInteger` stores the `(NULLA, 0)` pair.
- Optimized conversion function integer --> roman
- Set regex fields as static in `RomanNumeral` for memeory optimization
- Set `RomanConverter` field as static in `RomanInteger` for memory 
  optimization
- This changelog file format
- Images and sections in readme file

Removed
-------
- Package `core`: all contained classes moved to `it.matjaz.jnumerus`

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
- Library logo in `.svg` and `.png` with CC SA-4.0 license
- Much much more Trello documentation, board background and picures

Fixed
-----
- Missing or error-containing license headers in some source files


v0.3.0
======
Codename: **Tres**  
Build day: 41

Added
-----
- Mozilla Public License v2.0 to project and source files
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
- Bidirectional conversions roman numeral <--> arabic integer, performed 
  by `RomanConverter`


v0.1.0
======
Codename: **Unus**  
Build day: 38

Added
-----
- Project initialization to Java 1.8, Maven, JUnit. Project id: 
  `it.matjaz.jnumerus`
- Structures of pairs (basic roman character, its `int` value) to be 
  used for conversion, generated by `RomanCharMapFactory`
