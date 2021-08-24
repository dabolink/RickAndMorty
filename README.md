# Rick And Morty Wiki

## what is this?
this App uses the rick and morty API (https://rickandmortyapi.com/documentation) to generate a wiki of data about
characters, locations and episodes

currently it has list of all values of each type and can navigate to individual characters in order to see an image of them

still to be added is:
- ability to go to episodes/locations/characters from other episodes/locations/characters
- ability to store data offline using a database (room probably)
- better UI components (not my forte)

# Dependencies
    - OKHttp (REST API calls)
    - Glide (images)
    - Android Support Libraries
        - fragments
        - view binding
        - ViewPager2 + tablayout
        - ViewModels/LiveData

# TODO
    - [x] list items
        - episodes
        - locations
        - characters
    - [x]paging
    - [x]individual pages
    - [x]get images
    - [] background
    - [] more pleasant UI
    - [] favorite items?

# ISSUES
- [x] on back recyclerview not redrawing
