[33mcommit 7fd0f578ec740fa5579978ab63b58b16af463289[m[33m ([m[1;36mHEAD -> [m[1;32mSignup[m[33m)[m
Author: Quiescent <fallenartx@gmail.com>
Date:   Tue Aug 8 21:51:56 2023 +1000

    Authentication is up and running, adds additional security dependencies into pom.xml as well as auth/user package, and JwtService, JwtAuthenticationFilter, ApplicationConfig, there's so deprecation errors but they still technically run so I'll just leave them as is for now.

[33mcommit 22159edbf0498c0aaa4925b00afb08ff74cfe997[m[33m ([m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m, [m[1;32mmain[m[33m)[m
Merge: 369640b 279fa4a
Author: anton-lyamin <93956697+anton-lyamin@users.noreply.github.com>
Date:   Thu Aug 10 18:49:34 2023 +1000

    Merge pull request #3 from anton-lyamin/Create-jpa-entities
    
    Create jpa entities

[33mcommit 279fa4ad48e0d449f3218d28b0d470226f7cc0dc[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Thu Aug 10 18:43:01 2023 +1000

    Add/Update Getters/Setters and Relation management methods

[33mcommit 128f118578966ddaca547917d0cbdbf03e1236f3[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Thu Aug 10 18:42:19 2023 +1000

    Move relation mappings from Id to entity

[33mcommit 54498fd4e19f09d2437f88b29c4bf2b9e789fdc4[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Thu Aug 10 18:33:25 2023 +1000

    Rename Distances and DistancesId

[33mcommit 6212b92afa82635457407aecd39a7ee64d5c947b[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 9 20:20:27 2023 +1000

    Update data.sql to set/unset foreign key checks

[33mcommit a30e1d616458d281b5cecc0e0c528ee7688fff56[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Tue Aug 8 22:51:48 2023 +1000

    Comment out availability table insert statements

[33mcommit a834ab451b707cf40d2c11ff83e27a688b97fcf3[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Tue Aug 8 22:50:48 2023 +1000

    Add missing PlaneType table in schema for reference

[33mcommit 5ea6d7acfa89090f2296f05d97f49f5a7e9306b0[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Tue Aug 8 22:49:47 2023 +1000

    Remove unused imports

[33mcommit 19c306790a943f8aee0e60adc2cdb114ec89d1a7[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Tue Aug 8 22:35:47 2023 +1000

    Fix entities and run data insertion script
    - setup all relationships between entities
    - check they exist as foreign keys in database
    - run data.sql to check data is inserted correctly
    - Availability table does not take in data due to foreign key constraint to be solved in another branch

[33mcommit 23566e53c0b46edcbc0f253bc4c776c73d13cfe2[m
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Mon Aug 7 18:18:39 2023 +1000

    Follow up: Created getters/setters for each entity

[33mcommit cc7c2a5d09826ccfc9b94ec637f5c90b7332becf[m
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Mon Aug 7 18:13:46 2023 +1000

    Edited entities to have HashSets for foreign keys

[33mcommit ecac3542e2adace8f6afbd1049dcc7141dbc7750[m
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Thu Aug 3 21:51:05 2023 +1000

    Follow up: Create entities and finish definitions

[33mcommit 9d53c216df6a51b9df451125f7674fd643f239d3[m
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Thu Aug 3 21:49:24 2023 +1000

    Create rest of entities and finish definitions

[33mcommit 369640b95e3fc8609c0efe416ef360fe93ec9f5c[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Sat Aug 5 22:04:19 2023 +1000

    Remove unnecessary files

[33mcommit b0c6df599d33ec78c80ded9e01e42da5c82a0dab[m
Merge: fa0d680 31343e9
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Thu Aug 3 15:32:51 2023 +1000

    Fixed merge conflicts in Entity classes

[33mcommit 31343e938bb6f6fc3afc7491db2cca19c8d1abb5[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 2 20:38:42 2023 +1000

    Add and update insert queries and schema
    - add insert queries into data.sql
    - modify Country table to contain columns found in accompanying insert query
    - convert all column names and references for Country from camelCase to pascalCase
    - comment out insert queries for tables that arent yet mapped

[33mcommit 7e5992afc639ff66cf8447a42fc6b04a7b56a444[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 2 20:08:53 2023 +1000

    Update Spring application properties
    
    - Configure spring to always run sql scripts at initalization
    - Defer initialization of data scripts to occur after hibernate initialization
    - set only data script to be run, not schema
    - set hibernate to always drop and recreate schema at initialization

[33mcommit 786ec326622125ebe63bcca49aa3f75d92ef3baa[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 2 17:39:04 2023 +1000

    Create entities for basic Flight Search

[33mcommit fa0d680a118c50868ea43a3b697d8eba780a6522[m
Merge: 84372bc 3ae2cf9
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Wed Aug 2 17:39:32 2023 +1000

    Merge branch 'main' of https://github.com/anton-lyamin/FlightPub

[33mcommit 3ae2cf92aeaa321344385ffbb86d114a64db469c[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 2 17:39:04 2023 +1000

    Create entities for basic Flight Search

[33mcommit 84372bc3ab67690be9c1c31eb7a3e39a755115ba[m
Merge: a438db9 ebbfcc9
Author: Robert Duggan <rob.m.duggan@gmail.com>
Date:   Wed Aug 2 17:31:04 2023 +1000

    Merge branch 'main' of https://github.com/anton-lyamin/FlightPub

[33mcommit ebbfcc9206430495ca9b9f4c0b81f1ef6dd34f14[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Wed Aug 2 11:08:58 2023 +1000

    Setup PascalCase naming strategy for entity derived tables

[33mcommit f3e5118e8292a6d8057958bb5904d29cd831c413[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Tue Aug 1 14:08:49 2023 +1000

    docs: Update README instructions

[33mcommit 09f61621bfdc55f756ffc55d0da41b82c6072e5c[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Mon Jul 31 21:30:12 2023 +1000

    Create simple controller/view example

[33mcommit a438db9818b62b1caacc3c87b90d1a5da66c390f[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Mon Jul 31 21:30:12 2023 +1000

    Create simple controller/view example

[33mcommit ccc644b3a2ff544cfdeebba5ac3b8cc2daff7aa3[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Mon Jul 31 21:29:17 2023 +1000

    Setup dispatcher servlet configuration and initialization

[33mcommit 4049a62a6bc7c8301dd140b24105b680e7f6b214[m[33m ([m[1;31morigin/staging[m[33m)[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Sun Jul 30 16:58:01 2023 +1000

    docs: Update README

[33mcommit a62ecd072d0c65d740e21ade8c25c76f7a14ab58[m
Author: Anton Lyamin <antoine.lyamin@gmail.com>
Date:   Sun Jul 30 16:22:17 2023 +1000

    First commit

[33mcommit 2f3c6f421eef684d8c4b7676080efec57d77cb56[m
Author: anton-lyamin <93956697+anton-lyamin@users.noreply.github.com>
Date:   Sun Jul 30 16:06:21 2023 +1000

    Initial commit
