![example workflow](https://github.com/Tremirre/JSON-Derulo/actions/workflows/ci.yml/badge.svg)
![version](https://img.shields.io/github/v/release/Tremirre/JSON-Derulo?display_name=tag&include_prereleases)

# JSON-Derulo
2022 SE Java project<br>
[Backlog spreadsheet](https://github.com/Tremirre/JSON-Derulo/files/8747403/JSON.tools.xlsx)<br>

## About JSON-Derulo
Application for programmers who need to reformat or filter data structures saved in JSON format and to compare the structures with each other or find lines that contain a pre-defined string in the text. JSON tools application allows you to both minify the unminified JSON representation, as well as the reverse operation (with any blanks and new lines added). The application will be available via remote API, so it can be integrated with existing tools. 

## Application features
- minify/unminify a JSON file
- retain/remove specified keys from a JSON structure
- compare two texts and show lines that differ
- find lines that contain a pre-defined string in the text.

## API instructions
A user can create request using POST method and *transform* or *compare* endpoint in order for the appropriate action to be carried out.
- *transform* parameters:

parameter  | description
------------- | -------------
json|json file
actions|list of actions to be performed|
keys|keys to be removed or retained|

- *compare* parameters:

parameter  | description
------------- | -------------
jsons|list of json files to be compared

- *find* parameters:

parameter  | description
------------- | -------------
text|text to be searched
string|string to be found in the text

## POSTMAN API examples
Examples of both correct and incorrect requests using POSTMAN:<br><br>
**VALID TRANSFORMATION**:<br>
![obraz](https://user-images.githubusercontent.com/82370491/169814839-958dbf8b-0a6a-465a-9c9e-bd5df9fce499.png)<br><br>
**INVALID TRANSFORMATION:**<br>
![obraz](https://user-images.githubusercontent.com/82370491/169815401-0439965b-bd32-41cf-9a5b-8099e8d65641.png)<br><br>
**INVALID JSON:**<br>
![obraz](https://user-images.githubusercontent.com/82370491/169816237-2374fa9b-4219-4cf0-af40-6be2ed0daf6a.png)<br><br>
**TEXT COMPARISON:**<br>
![obraz](https://user-images.githubusercontent.com/82370491/173146419-793bede8-6487-4325-9325-82e06319b53a.png)<br><br>
**STRING SEARCH:**<br>
![obraz](https://user-images.githubusercontent.com/82370491/173146607-4a6350df-41f8-4502-8fca-8712115265f0.png)
