# MVVM_TrendingRepos

Highlights of this project----------------------------------------------------------------------------

1.  MVVM
2.  Retofit
3.  Gson
4.  RxJava
5.  Picasso
6.  100% Offline support (HTTP response caching & Network calls optimization)
7.  AndroidX
8.  MockWebServer for Junit test
9.  Esspresso for UI test
10. Expanding recyclerview item on click
11. Constraint Layout
12. Swipe down layout to refresh recyclerview
13. Round imageview using external liberary.

 Application Architecture and features-----------------------------------------------------------------------------------------------------------

- This application architecture follows the MVVM architecture , which provides reactive , lifecycle aware and smooth implementation flow.
- For achieveing this  RxJava2 along with Retrofit are used.
- For backward compatibility androidx has been used.
- Application provides flexibility to provide dynamic urls for web requsts as retrofit API has URL has parameters.
- Application basically fetches trending repositories and displays them in a recyclerview.

- Application supports Caching Http responses including images using OkHttp interceptors for Retrofit Requests for configurable timeout.
- once applucation has got the succussfull response from server , every consequent requests are intercepted by request interceptor of retrofit OkHttp client
  which returns repose from cache unless cache is not expired which helps app optimize bunch repeated network requests.
  
  
  ![](/assignment_1.gif)

