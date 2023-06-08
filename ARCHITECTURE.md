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
    B-->B1(HomepageViewHolder with Fragments);
    B1-->B2(HomepageFragment);
    B1-->B3(RentalsFragement);
    B1-->B4(BookmarksFragment);
    B1-->B5(ProfileFragment);
    C-->C1(PostLogic);
    C-->C2(UserLogic);
    D-->D1(PostPersistence);
    D-->D2(UserPersistence);
    E-->E1(Post);
    E-->E2(User);
    end
```

## Packages
- presentation: holds the UI code
- logic: holds the logic/business code
- persistence: holds the code relating to the database