insert into USERS(USER_ID, NAME, email, image_url, email_verified, password, provider, provider_id, enabled, reg_date)
    values(1L, '아인', 'ahin@palibra.net', '', false, '$2a$10$Eic33i6qP/lv3jjW3kBGmegaIqUyVDINLm/Aqkoy7k4/SQi7cMAhW', 'local', null, true, CURRENT_TIMESTAMP());

insert into LIBRA_ACCOUNT(LIBRA_ADDRESS,NAME,PRIVATE_KEY,PUBLIC_KEY, USER_ID)
    values ( 'F58BDE8F9995AD1D37F5131C1219B00851B52AF4A674236BF4C21294CD3BAA5E', 'LibraWallet', '3051020101300506032B65700422042094A23EA25E11378180C5D174C1AC3DDEC9F02F57F70BF3DE37F196C43E665C4E81210056A3BCC31E78F23A7AD7172256B5A122BB6B47739A1BA12FBE98CC86E9F3BDFE', '302A300506032B657003210056A3BCC31E78F23A7AD7172256B5A122BB6B47739A1BA12FBE98CC86E9F3BDFE', 1L );

insert into USERS(USER_ID, NAME, email, image_url, email_verified, password, provider, provider_id, enabled, reg_date)
    values(2L, '이환', 'ehwan@palibra.net', '', false, '$2a$10$Eic33i6qP/lv3jjW3kBGmegaIqUyVDINLm/Aqkoy7k4/SQi7cMAhW', 'local', null, true, CURRENT_TIMESTAMP());

insert into LIBRA_ACCOUNT(LIBRA_ADDRESS,NAME,PRIVATE_KEY,PUBLIC_KEY, USER_ID)
    values ( '8B3917E09655D9780B2A9269699E68B8A5E4BD1EA151BA1526B522C1D7F7CF72', 'LibraWallet', '3051020101300506032B6570042204205FC307F8C16E10F60E3A4D2D2026E931262538C3ABACB7E57EDC436448845440812100F6C6A292C11DA057EBC13E497BC3AE44B41907504AFC6247E541699B9B277F01', '302A300506032B6570032100F6C6A292C11DA057EBC13E497BC3AE44B41907504AFC6247E541699B9B277F01', 2L );


insert into USERS(USER_ID, NAME, email, image_url, email_verified, password, provider, provider_id, enabled, reg_date)
    values(3L, 'Young-Ho Kim', 'younghok@gmail.com', '', false, '', 'google', 106017450987201970826, true, CURRENT_TIMESTAMP());

insert into LIBRA_ACCOUNT(LIBRA_ADDRESS,NAME,PRIVATE_KEY,PUBLIC_KEY, USER_ID)
    values ( '7148A12815766282A0544822F0D16F7F570FBF3CBA559ABED42E1F3DA357A940', 'yhk-basic', '3051020101300506032B65700422042075E7B1B2A142DBF2174E03975CD21D5010F75B19211A719456BBAF4ADF19802E812100A032D380600C91472C9658FC670FE0C196633C1298F5E823949B15792D3897F7', '302A300506032B6570032100A032D380600C91472C9658FC670FE0C196633C1298F5E823949B15792D3897F7', 3L );

insert into LIBRA_ACCOUNT(LIBRA_ADDRESS,NAME,PRIVATE_KEY,PUBLIC_KEY, USER_ID)
values ( '18663F2D50BE4C806F0F86CA2869B3EA04C702298046258F4F40428C21EE4A6B', 'yhk-basic-1', '3051020101300506032B657004220420ED5A6F2A1CBEFE34E940BFAB2BDBF3F1574D322405731D69AE08025287BB036781210069F9EF1F7FA79AFFF4FE6590FF36FB07638D671B29BEF4E500A148481D22B3EF', '302A300506032B657003210069F9EF1F7FA79AFFF4FE6590FF36FB07638D671B29BEF4E500A148481D22B3EF', 3L );


insert into TEMP_TABLE(TEST_ID, NAME, EMAIL)
    values ( 1L, 'tester', 'test@test.com' );