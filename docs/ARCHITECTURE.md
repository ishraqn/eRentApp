# Architecture

## Diagram
Here is a visual representation of our current architecture

```mermaid
graph TD;
    subgraph "Architecture"
    A{eRent}-->B[presentation];
    A{eRent}-->C[logic];
    A{eRent}-->D[persistence];
    A{eRent}-->E[objects];
    A{eRent}-->F[application]
    B-->InitActivity;
    B-->LoginActivity;
    B-->SignUpActivity;
    B-->PostActivity;
    B-->ProfileActivity;
    B-->B1(HomepageViewHolder with Fragments);
    B1-->B2(HomepageFragment);
    B1-->B3(RentalsFragement);
    B1-->B4(BookmarksFragment);
    B1-->B5(ProfileFragment);
    C-->C1(PostLogic);
    C-->C2(UserLogic);
    C-->C3(BookmarkLogic);
    C-->C4(RentalLogic);
    D-->D1(PostPersistence);
    D-->D2(UserPersistence);
    D-->D3(BookmarkPersistence);
    E-->E1(Post);
    E-->E2(User);
    F-->F1(Main);
    F-->F2(Services)
    end
```

## Packages
- presentation: holds the UI code
- logic: holds the logic/business code
- persistence: holds the code relating to the database
- application: provides a single source of data the UI layer can use to access the logic layer through dependency injection