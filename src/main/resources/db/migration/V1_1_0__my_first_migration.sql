drop table if exists address CASCADE;
drop table if exists journalist CASCADE;
create table address (
                        id bigint generated by default as identity,
                        address2 varchar(255),
                        city varchar(255),
                        country varchar(255),
                        media_outlet_address varchar(255),
                        state varchar(255),
                        zip_code varchar(255),
                        primary key (id)
                     );
create table journalist (
                            id bigint generated by default as identity,
                            email_address varchar(255),
                            facebook_url varchar(255),
                            first_name varchar(255),
                            instagram_url varchar(255),
                            last_name varchar(255),
                            linked_in_url varchar(255),
                            media_outlet_phone_number varchar(255),
                            media_outlets varchar(255),
                            title varchar(255),
                            twitter_name varchar(255),
                            twitter_url varchar(255),
                            address_id bigint,
                            primary key (id)
                        );
alter table journalist
    add constraint journalist_address_fk
        foreign key (address_id) references address;
