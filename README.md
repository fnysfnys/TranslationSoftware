# translationSoftware
open source translation software with translation memory (Work in progress)

## dependencies:
* commons-collections4-4
* commons-compress-1.21
* poi-5.0.0
* xmlbeans-5.0.1

## To Do:
* ~~Allow for old translations to be added to memory~~
* ~~Exporting with correct formatting (use runs)~~
* ~~Try custom file types rather than tmp for serialized objects (.smrtt) (.tmem)~~
* Allow for translations to be recognised where dates have been changed, create regex for swedish and english date formats
* Handle dependancies with Maven rather than manually
* Check for if you can auto translate fragments of a sentence
* Auto translate if only one word has changed
* Implement fuzzy matching with levenstein distance
* check for string numbers e.g. (ett, två, etc.)
* fix bug where numbers arent found and hash stays when taken from memory


