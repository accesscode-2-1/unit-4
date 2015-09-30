##Project: Build an API Server
Deliverable: A hosted API server (potentially free heroku account) written in Node.js that displays data relevant to the android app you built.  This app should take advantage of data that you are collecting from the app and storing (in Parse or Firebase).  If your app does not collect data this API should use other APIs used by your app to provide a similar set of data provided to the application.  This app should use data collected by your android app to provide metrics such as usage, types of requests, frequency of data submitted, etc.


If your app does not currently collect data and store it in a database we will assign a datasource to you to build your API against.  The API only needs to respond to requests and should provide only data that you are comfortable sharing with the public.  The API will also need to be documented with URLs and the list of parameters required for each call as well as the return types.


#### Directory Structure Example
```
ProjectFolder
├── package.json
├── server.js (or whatever you call it)
├── www (web server root folder)
│   ├── index.html
│   ├── js
│   ├── img
│   └── css
├── lib
│   ├── config.json (don't commit to repo, .gitignore this)
│   └── api
│       └── v1
│           └── test.js
│           └── [other routes].js
```

#### API Design Pattern
As discussed in class your project will only require you to retrieve data so you can limit it to get.  If you'd like to attempt POST calls.

| Route  | HTTP Verb  | Description |
| :------------ |:---------------|:-----|
| /api/v1/bears | GET | Get all the bears. |
| /api/v1/bears | POST | Create a bear. |
| /api/v1/bears/:bear_id | GET | Get a single bear. |
| /api/v1/bears/:bear_id | PUT | Update a bear with new info. |
| /api/v1/bears/:bear_id | DELETE | Delete a bear. |


