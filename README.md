# dnsproject
This project describe how to Login and get jwt. After jwt generated. User can Access Protected Resources Using JWT

How to run this project
1. import the project into intellij idea/other idea
2. adjust the database configuration, with your database environment
3. run this query 
--  Auto-generated SQL script #202212171405
INSERT INTO `dns-multi-pro`.`user` (id,address,email,name,password,`role`,user_name)
VALUES (1,'Jakarta Selatan','aminturmudzi@gmail.com','amin','$2a$10$HjMnse/X1QdIwZ8aH.G8KONdz4wUPo/53bW/NI5/8Kjo3YkBcVm5W','ADMIN','admin1');
4. run main class
NOTE : the password is password123
# How to test the api
## first scenario
1. user hit login url without enter username and password
2. user hit login url and insert not valid(username and password)
3. user hit login url using valid username and password
## second scenario
1. user hit protected api without login
2. user hit protected resources  after login
  ### A. hit job list. 
   - hit job list, with existing jobs list in external API.
   ### B. hit job get by id.
   - get job by id, with id not found in external API.
   - get job by id, with exist data in external API.
