set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;


INSERT INTO FLUG VALUES (91, 'AC', 10, 'YYZ', 'FRA', 1815, 740);                                    
INSERT INTO FLUG VALUES (52, 'AC', 11, 'YUL', 'YYZ', 1500, 1700);                                   
INSERT INTO FLUG VALUES (18, 'AF', 9, 'TXL', 'CDG', 915, 1040);                                     
INSERT INTO FLUG VALUES (74, 'AF', 33, 'FRA', 'CDG', 900, 1010);                                    
INSERT INTO FLUG VALUES (84, 'AF', 34, 'FRA', 'CDG', 1200, 1210);                                   
INSERT INTO FLUG VALUES (1, 'AF', 35, 'CDG', 'FRA', 1400, 1500);                                    
INSERT INTO FLUG VALUES (92, 'AF', 45, 'CDG', 'NRT', 1220, 730);                                    
INSERT INTO FLUG VALUES (2, 'BA', 84, 'ORD', 'SFO', 1530, 1800);                                    
INSERT INTO FLUG VALUES (66, 'BA', 86, 'FRA', 'LHR', 910, 1050);                                    
INSERT INTO FLUG VALUES (3, 'BA', 87, 'LHR', 'FRA', 1310, 1450);                                    
INSERT INTO FLUG VALUES (4, 'BA', 88, 'LHR', 'BHX', 1010, 1050);                                    
INSERT INTO FLUG VALUES (5, 'BA', 90, 'LGW', 'BHX', 1020, 1150);                                    
INSERT INTO FLUG VALUES (36, 'DB', 2, 'HHN', 'FRA', 1120, 1230);                                    

INSERT INTO FLUG VALUES (85, 'DB', 3, 'FRA', 'HHN', 1530, 1640);                                    
INSERT INTO FLUG VALUES (19, 'DB', 6, 'ERF', 'FRA', 915, 945);                                      
INSERT INTO FLUG VALUES (75, 'DB', 7, 'FRA', 'ERF', 1000, 1030);                                    
INSERT INTO FLUG VALUES (20, 'DB', 8, 'MIR', 'FRA', 1000, 1230);                                    
INSERT INTO FLUG VALUES (21, 'DB', 9, 'FRA', 'MIR', 1500, 1730);                                    
INSERT INTO FLUG VALUES (67, 'DB', 10, 'DJE', 'FRA', 1000, 1230);                                   
INSERT INTO FLUG VALUES (6, 'DB', 11, 'FRA', 'DJE', 1500, 1730);                                    
INSERT INTO FLUG VALUES (76, 'DB', 14, 'LYS', 'FRA', 1845, 1955);                                   
INSERT INTO FLUG VALUES (37, 'DB', 15, 'FRA', 'LYS', 115, 230);                                     
INSERT INTO FLUG VALUES (38, 'DB', 16, 'PMI', 'FRA', 455, 650);                                     
INSERT INTO FLUG VALUES (68, 'DB', 17, 'FRA', 'PMI', 915, 1150);                                    
INSERT INTO FLUG VALUES (93, 'DB', 18, 'DRS', 'FRA', 600, 700);                                     
INSERT INTO FLUG VALUES (77, 'DB', 19, 'FRA', 'DRS', 1750, 1850);                                   

INSERT INTO FLUG VALUES (39, 'DB', 20, 'ALF', 'FRA', 630, 700);                                     
INSERT INTO FLUG VALUES (78, 'DB', 21, 'FRA', 'ALF', 930, 1050);                                    
INSERT INTO FLUG VALUES (86, 'DB', 22, 'PEK', 'FRA', 0, 800);                                       
INSERT INTO FLUG VALUES (53, 'DB', 23, 'FRA', 'PEK', 1500, 2300);                                   
INSERT INTO FLUG VALUES (40, 'DB', 24, 'KLU', 'FRA', 1050, 1250);                                   
INSERT INTO FLUG VALUES (22, 'DB', 25, 'FRA', 'KLU', 1450, 1650);                                   
INSERT INTO FLUG VALUES (23, 'DB', 28, 'TUN', 'FRA', 1000, 1230);                                   
INSERT INTO FLUG VALUES (24, 'DB', 29, 'FRA', 'TUN', 1500, 1730);                                   
INSERT INTO FLUG VALUES (94, 'DB', 32, 'MPL', 'FRA', 820, 1050);                                    
INSERT INTO FLUG VALUES (69, 'DB', 33, 'FRA', 'MPL', 1210, 1440);                                   
INSERT INTO FLUG VALUES (25, 'DB', 36, 'HAJ', 'FRA', 800, 900);                                     
INSERT INTO FLUG VALUES (26, 'DB', 37, 'FRA', 'HAJ', 1200, 1300);                                   
INSERT INTO FLUG VALUES (95, 'DB', 40, 'YYC', 'FRA', 815, 1715);                                    

INSERT INTO FLUG VALUES (27, 'DB', 41, 'FRA', 'YYC', 1830, 330);                                    
INSERT INTO FLUG VALUES (28, 'DB', 42, 'SSH', 'FRA', 500, 900);                                     
INSERT INTO FLUG VALUES (29, 'DB', 43, 'FRA', 'SSH', 1100, 1400);                                   
INSERT INTO FLUG VALUES (7, 'DB', 46, 'LIM', 'FRA', 2055, 1830);                                    
INSERT INTO FLUG VALUES (79, 'DB', 47, 'FRA', 'LIM', 2015, 820);                                    
INSERT INTO FLUG VALUES (8, 'DB', 48, 'DUS', 'FRA', 915, 1000);                                     
INSERT INTO FLUG VALUES (41, 'DB', 49, 'FRA', 'DUS', 1115, 1200);                                   
INSERT INTO FLUG VALUES (96, 'DB', 54, 'SVO', 'FRA', 1620, 1745);                                   
INSERT INTO FLUG VALUES (80, 'DB', 55, 'FRA', 'SVO', 1845, 2010);                                   
INSERT INTO FLUG VALUES (87, 'DB', 56, 'BRE', 'FRA', 615, 700);                                     
INSERT INTO FLUG VALUES (9, 'DB', 57, 'FRA', 'BRE', 800, 845);                                      
INSERT INTO FLUG VALUES (54, 'DB', 58, 'LPA', 'FRA', 100, 550);                                     
INSERT INTO FLUG VALUES (10, 'DB', 59, 'FRA', 'LPA', 1700, 2150);                                   

INSERT INTO FLUG VALUES (42, 'DB', 60, 'ISA', 'FRA', 415, 32);                                      
INSERT INTO FLUG VALUES (43, 'DB', 61, 'FRA', 'ISA', 1750, 909);                                    
INSERT INTO FLUG VALUES (55, 'DB', 62, 'HKG', 'FRA', 1000, 1700);                                   
INSERT INTO FLUG VALUES (56, 'DB', 63, 'FRA', 'HKG', 1900, 800);                                    
INSERT INTO FLUG VALUES (11, 'DB', 72, 'YDE', 'FRA', 2315, 725);                                    
INSERT INTO FLUG VALUES (57, 'DB', 73, 'FRA', 'YDE', 1740, 2340);                                   
INSERT INTO FLUG VALUES (58, 'DB', 74, 'CGK', 'FRA', 2300, 1000);                                   
INSERT INTO FLUG VALUES (12, 'DB', 75, 'FRA', 'CGK', 1700, 1300);                                   
INSERT INTO FLUG VALUES (70, 'DB', 76, 'MVR', 'FRA', 1000, 1700);                                   
INSERT INTO FLUG VALUES (59, 'DB', 77, 'FRA', 'MVR', 1800, 100);                                    
INSERT INTO FLUG VALUES (44, 'DB', 78, 'TLS', 'FRA', 830, 1000);                                    
INSERT INTO FLUG VALUES (71, 'DB', 79, 'FRA', 'TLS', 1230, 1400);                                   
INSERT INTO FLUG VALUES (72, 'DB', 80, 'SZG', 'FRA', 800, 1000);                                    

INSERT INTO FLUG VALUES (45, 'DB', 81, 'FRA', 'SZG', 1200, 1400);                                   
INSERT INTO FLUG VALUES (60, 'DB', 82, 'CRT', 'FRA', 1000, 1230);                                   
INSERT INTO FLUG VALUES (61, 'DB', 83, 'FRA', 'CRT', 1500, 1730);                                   
INSERT INTO FLUG VALUES (46, 'DB', 90, 'TRU', 'FRA', 2200, 1020);                                   
INSERT INTO FLUG VALUES (30, 'DB', 91, 'FRA', 'TRU', 1430, 2200);                                   
INSERT INTO FLUG VALUES (13, 'DL', 7, 'ORD', 'SFO', 1140, 1530);                                    
INSERT INTO FLUG VALUES (14, 'DL', 9, 'LAX', 'NRT', 2220, 1000);                                    
INSERT INTO FLUG VALUES (97, 'DL', 33, 'SFO', 'LAX', 900, 1005);                                    
INSERT INTO FLUG VALUES (98, 'JL', 12, 'TXL', 'KIX', 1355, 820);                                    
INSERT INTO FLUG VALUES (31, 'LH', 5, 'FRA', 'TXL', 730, 830);                                      
INSERT INTO FLUG VALUES (62, 'LH', 6, 'TXL', 'FRA', 930, 1030);                                     
INSERT INTO FLUG VALUES (88, 'LH', 7, 'FRA', 'TXL', 1130, 1230);                                    
INSERT INTO FLUG VALUES (63, 'LH', 8, 'TXL', 'FRA', 1230, 1330);                                    

INSERT INTO FLUG VALUES (47, 'LH', 9, 'FRA', 'TXL', 1430, 1530);                                    
INSERT INTO FLUG VALUES (48, 'LH', 10, 'TXL', 'FRA', 1630, 1730);                                   
INSERT INTO FLUG VALUES (15, 'LH', 20, 'TXL', 'CDG', 900, 1140);                                    
INSERT INTO FLUG VALUES (32, 'LH', 24, 'TXL', 'FRA', 2130, 2310);                                   
INSERT INTO FLUG VALUES (89, 'LH', 32, 'JFK', 'LAX', 1400, 1800);                                   
INSERT INTO FLUG VALUES (16, 'LH', 34, 'FRA', 'SFO', 1015, 1245);                                   
INSERT INTO FLUG VALUES (49, 'LH', 36, 'SFO', 'LAX', 1700, 1815);                                   
INSERT INTO FLUG VALUES (90, 'LH', 40, 'FRA', 'LHR', 700, 815);                                     
INSERT INTO FLUG VALUES (99, 'LH', 41, 'LHR', 'FRA', 1025, 1145);                                   
INSERT INTO FLUG VALUES (50, 'LH', 42, 'FRA', 'LHR', 1300, 1415);                                   
INSERT INTO FLUG VALUES (81, 'LH', 43, 'LHR', 'FRA', 1515, 1640);                                   
INSERT INTO FLUG VALUES (64, 'LH', 44, 'FRA', 'LHR', 1600, 1715);                                   
INSERT INTO FLUG VALUES (17, 'LH', 45, 'LHR', 'FRA', 1830, 2000);                                   

INSERT INTO FLUG VALUES (33, 'LH', 46, 'FRA', 'LHR', 2130, 2245);                                   
INSERT INTO FLUG VALUES (51, 'LH', 47, 'LHR', 'FRA', 700, 820);                                     
INSERT INTO FLUG VALUES (34, 'LH', 50, 'FRA', 'CDG', 710, 810);                                     
INSERT INTO FLUG VALUES (100, 'LH', 51, 'CDG', 'FRA', 930, 1030);                                   
INSERT INTO FLUG VALUES (82, 'LH', 52, 'FRA', 'CDG', 1210, 1310);                                   
INSERT INTO FLUG VALUES (35, 'LH', 53, 'CDG', 'FRA', 1500, 1620);                                   
INSERT INTO FLUG VALUES (73, 'LH', 54, 'FRA', 'CDG', 1740, 1850);                                   
INSERT INTO FLUG VALUES (83, 'LH', 55, 'CDG', 'FRA', 2000, 2120);                                   
INSERT INTO FLUG VALUES (65, 'LH', 60, 'FRA', 'BHX', 1005, 1130);                                   


commit;
  