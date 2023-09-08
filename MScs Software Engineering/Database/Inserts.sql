--Category list from Seek.com.au
INSERT INTO dbo.JOB_CATEGORY (cat_name) VALUES
('Select a Category'),
('Accounting'),
('Administration & Office Support'),
('Advertising, Arts & Media'),
('Banking & Financial Services'),
('Call Centre & Customer Service'),
('CEO & General Management'),
('Community Services & Development'),
('Construction'),
('Consulting & Strategy'),
('Design & Architecture'),
('Education & Training'),
('Engineering'),
('Farming, Animals & Conservation'),
('Government & Defence'),
('Healthcare & Medical'),
('Hospitality & Tourism'),
('Human Resources & Recruitment'),
('Information & Communication Technology'),
('Insurance & Superannuation'),
('Legal'),
('Manufacturing, Transport & Logistics'),
('Marketing & Communications'),
('Mining, Resources & Energy'),
('Real Estate & Property'),
('Retail & Consumer Products'),
('Sales'),
('Science & Technology'),
('Self Employment'),
('Sport & Recreation'),
('Trades & Services')
;

--Skills list from 20 most in-demand skills, LinkedIn blog 20/2/2023
INSERT INTO dbo.SKILLS (skill_name) VALUES
('Management'),
('Communication'),
('Customer Service'),
('Leadership'),
('Sales'),
('Project Management'),
('Research'),
('Analytical Skills'),
('Marketing'),
('Teamwork'),
('Software Development'),
('SQL'),
('Finance'),
('Python'),
('Java'),
('Data Analysis'),
('JavaScript'),
('Cloud Computing'),
('Operations'),
('Customer Relationship Management (CRM)')
;

--Insert user types
INSERT INTO dbo.USER_TYPE (user_type) VALUES
    ('Administrator'),
    ('Job Seeker'),
    ('Job Seeker&Recruiter'),
    ('Recruiter')
;

INSERT INTO dbo.EMPLOYMENT_TYPE(emp_type_name) VALUES
    ('Full Time'),
    ('Part Time'),
    ('Casual'),
    ('Remote')
;

INSERT INTO dbo.LOCATION(loc_name) VALUES
    ('Australia'),
    ('Australian Capital Territory'),
    ('New South Wales'),
    ('Northern Territory'),
    ('Queensland'),
    ('South Australia'),
    ('Tasmania'),
    ('Victoria'),
    ('Western Australia')
;

--Insert users
INSERT INTO dbo.USER_ACCOUNT(user_type, first_name, last_name, email, password, loc_id) VALUES
    (1, 'Admin', 'Admin', 'admin@admin.com', 'admin', 1),
    (2, 'Job', 'Seeker', 'job@seeker.com', 'password', 1),
    (4, 'Recruit', 'Recruiter', 'recruit@jss.com', 'password', 1)
;