create table users
(
    admin         bit          not null,
    enabled       bit          not null,
    created_at    datetime(6)  not null,
    created_by_id bigint       null,
    id            bigint auto_increment
        primary key,
    updated_at    datetime(6)  not null,
    updated_by_id bigint       null,
    email         varchar(255) not null,
    name          varchar(255) not null,
    password      varchar(255) not null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint FK6nm9u1qpw9xxphk70xr614m7n
        foreign key (updated_by_id) references users (id)
            on delete set null,
    constraint FK8nakkftyppd62ke6tv7oo5a92
        foreign key (created_by_id) references users (id)
            on delete set null
);

create table categories
(
    id            bigint auto_increment
        primary key,
    created_at    datetime(6)  not null,
    created_by_id bigint       null,
    updated_at    datetime(6)  not null,
    updated_by_id bigint       null,
    name          varchar(255) not null,
    constraint FK73a3wvxd29tadruahdvn00ljv
        foreign key (updated_by_id) references users (id)
            on delete set null,
    constraint FKb07hhhlceyn1pkhlw2xb99par
        foreign key (created_by_id) references users (id)
            on delete set null
);

create table posts
(
    created_at    datetime(6)  not null,
    created_by_id bigint       null,
    id            bigint auto_increment
        primary key,
    updated_at    datetime(6)  not null,
    updated_by_id bigint       null,
    content       text         not null,
    slug          varchar(255) not null,
    title         varchar(255) not null,
    constraint UK_qmmso8qxjpbxwegdtp0l90390
        unique (slug),
    constraint FK2pi26lplw3d2i5w7ueidhb981
        foreign key (created_by_id) references users (id)
            on delete set null,
    constraint FK96om7kvihl27ud3k6r27k0i2y
        foreign key (updated_by_id) references users (id)
            on delete set null
);

create table categories_posts
(
    category_id bigint not null,
    post_id     bigint not null,
    primary key (category_id, post_id),
    constraint FK74riv8mw4e9shd1b1gpce0j43
        foreign key (post_id) references posts (id),
    constraint FK9juf11r7w0xqrwrfig4q1gqi5
        foreign key (category_id) references categories (id)
);

create table comments
(
    id            bigint auto_increment
        primary key,
    created_at    datetime(6) not null,
    created_by_id bigint      null,
    updated_at    datetime(6) not null,
    updated_by_id bigint      null,
    post_id       bigint      not null,
    content       text        not null,
    constraint FKakkm6qfydu7vgnfne1yo0xmed
        foreign key (created_by_id) references users (id)
            on delete set null,
    constraint FKh4c7lvsc298whoyd4w9ta25cr
        foreign key (post_id) references posts (id),
    constraint FKngfwojpnh2ftc2yrduv1iygju
        foreign key (updated_by_id) references users (id)
            on delete set null
);

