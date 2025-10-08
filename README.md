# Technical Interview - Code Review Exercise

Part of our engineering hiring process is a technical interview containing a live code review exercise. 

During the technical interview, you will be asked to review the code in this repository, as though it had been submitted as a PR.

## Instructions

You will be asked to load the project in Android Studio, share your screen, take us through the project and share your thoughts about it. Once you are done, we will ask some follow-up questions.

You may raise whatever points you wish about the project, but keep in mind that this is for a hypothetical pull request. You should look for ways to improve the code, and things that ought to be fixed before the code is merged, just as you would with a real pull request.

You should think about the following:
- What you like about the codebase.
- What should be improved.
- Other technologies you would use in place of or in addition to the ones included.
- What is the infrastructure in use, and what design patterns are evident? Are these appropriate for the project and used correctly?

Treat this as a first phase of an app that will be extended and made production-ready.

Please have the project loaded and ready to go in Android Studio at the start of the interview.

This is a code review exercise, not a coding exercise, so please do not edit the code prior to the interview. You are encouraged to familiarise yourself with the code, however.

For context, the code in this repository is a response to this take-home coding challenge:

---

## Articles Headlines App

This App displays news articles.

- **Headlines**: screen to display a list of headlines based on the user-selected sources, each row should display (title, description, author, and thumbnail picture)
- **Read Article**: Tapping on a row should open the headline URL to read the full article within the app.
- Integrate the app with [News API](https://newsapi.org/) (register to get your API Key)
