-- DATABASE: [Shopper]
-----------------------------------------------------------------------------------------------------------------------
USE [master];
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'Shopper')
BEGIN
    ALTER DATABASE [Shopper] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [Shopper];
END;

CREATE DATABASE [Shopper];
GO

USE [Shopper];
GO 

-- TABLE: [Category]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Category]
(
    [categoryId] INT PRIMARY KEY IDENTITY,
    [categoryName] NVARCHAR(255) UNIQUE NOT NULL,
    [categoryImg] NVARCHAR(255)
);
GO

INSERT INTO [Category](categoryName, categoryImg)
VALUES (N'Giày', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/xxx_4.jpg?1723383697543'),
       (N'Dép', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/xxx_5.jpg?1723383697543'),
       (N'Phụ kiện', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/xxx_6.jpg?1723383697543');
GO

-- TABLE: [Brand]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Brand]
(
    [brandId] INT PRIMARY KEY IDENTITY,
    [brandName] NVARCHAR(255) UNIQUE NOT NULL,
    [brandImg] NVARCHAR(255)
);
GO

INSERT INTO [Brand](BrandName, brandImg)
VALUES (N'Nike', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/brand1.png?1723383697543'),
       (N'Adidas', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/brand2.png?1723383697543'),
       (N'Jordan', N'https://bizweb.dktcdn.net/100/377/398/themes/755909/assets/brand3.png?1728460629120');
GO

-- TABLE: [Color]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Color]
(
    [colorId] INT PRIMARY KEY IDENTITY,
    [colorName] NVARCHAR(255) UNIQUE NOT NULL,
    [hex] NVARCHAR(255) UNIQUE NOT NULL
);
GO

INSERT INTO [Color](colorName, hex)
VALUES
	(N'Đen', N'#202124'),
    (N'Trắng', N'#F1F3F4'),
    (N'Xám', N'#9AA0A6'),
    (N'Lam', N'#4285F4'),
    (N'Lục', N'#34A853'),
    (N'Vàng', N'#FBBC04'),
    (N'Cam', N'#E37400'),
    (N'Hồng', N'#673ab7'),
    (N'Đỏ', N'#EA4335');
GO

-- TABLE: [Product]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Product]
(
    [productId] INT PRIMARY KEY IDENTITY,
    [productName] NVARCHAR(255) NOT NULL,
    [productImg] NVARCHAR(MAX) NOT NULL,
    [price] MONEY NOT NULL,
	[categoryId] INT NOT NULL REFERENCES [Category](categoryId),
	[brandId] INT NOT NULL REFERENCES [Brand](brandId),
    [colorId] INT NOT NULL REFERENCES [Color](colorId),
	[discontinue] BIT NOT NULL DEFAULT 0
);
GO

INSERT INTO [Product](productName, categoryId, brandId, colorId, price, productImg)
VALUES
	(N'AIR JORDAN 1 RETRO HIGH OG "TAXI YELLOW/BLACK"', 1, 1, 6, 2670000, 'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-13t114937-353.png?v=1663044584103'),
	(N'AIR JORDAN 1 LOW "WHITE/RED"', 1, 1, 9, 1950000, 'https://bizweb.dktcdn.net/100/377/398/products/553560-164-71b6af1b-7d2b-494c-a18b-3e35b9492686.png?v=1659597526047'),
	(N'AIR JORDAN 1 MID "DARK TAIL GREEN"', 1, 1, 5, 2310000, 'https://bizweb.dktcdn.net/100/377/398/products/bq6472-308.png?v=1655891854563'),
	(N'AIR JORDAN 1 RETRO HIGH OG "HERITAGE"', 1, 1, 9, 2910000, 'https://bizweb.dktcdn.net/100/377/398/products/575441-161.png?v=1655383540113'),
	(N'AIR JORDAN 1 LOW SE "WHITE/LIGHT CURRY/MEDIUM OLIVE"', 1, 1, 6, 2550000, 'https://bizweb.dktcdn.net/100/377/398/products/dj0342-102.png?v=1655383653613'),
	(N'AIR JORDAN 1 LOW "BRED TOE"', 1, 1, 9, 3390000, 'https://bizweb.dktcdn.net/100/377/398/products/553558-612.png?v=1655385426877'),
	(N'AIR JORDAN 1 MID SE "WHITE SESAME"', 1, 1, 2, 1950000, 'https://bizweb.dktcdn.net/100/377/398/products/cz0774-200.png?v=1655385103963'),
	(N'AIR JORDAN 1 LOW SE "WHITE SESAME"', 1, 1, 2, 2550000, 'https://bizweb.dktcdn.net/100/377/398/products/dc9509-100.png?v=1655383187820'),
	(N'AIR JORDAN 1 HIGH RETRO OG "UNIVERSITY BLUE"', 1, 1, 4, 7500000, 'https://bizweb.dktcdn.net/100/377/398/products/575441-134.png?v=1655385542363'),
	(N'AIR JORDAN 1 LOW "UNIVERSITY GYM RED"', 1, 1, 9, 2550000, 'https://bizweb.dktcdn.net/100/377/398/products/553560-118.png?v=1655378083247'),
	(N'AIR JORDAN 1 MID CHICAGO "BLACK TOE"', 1, 1, 9, 3870000, 'https://bizweb.dktcdn.net/100/377/398/products/554725-069.png?v=1655378863527'),
	(N'AIR JORDAN 1 HIGH ZOOM AIR CMFT "PINK GLAZE"', 1, 1, 8, 5700000, 'https://bizweb.dktcdn.net/100/377/398/products/ct0979-601.png?v=1655384836050'),
	(N'AIR JORDAN 1 MID "UNIVERSITY GOLD"', 1, 1, 6, 3150000, 'https://bizweb.dktcdn.net/100/377/398/products/554724-170.png?v=1655382760900'),
	(N'AIR JORDAN 1 RETRO HIGH OG SILVER TOE "BLACK/METALLIC SILVER"', 1, 1, 1, 5340000, 'https://bizweb.dktcdn.net/100/377/398/products/cd0461-001.png?v=1655384019787'),
	(N'AIR JORDAN 1 HIGH ZOOM AIR CMFT "CANYON RUST/PURPLE SMOKE', 1, 1, 8, 5340000, 'https://bizweb.dktcdn.net/100/377/398/products/ct0979-602.png?v=1655385592087'),
	(N'AIR JORDAN 1 MID "BLACK CHILE RED WHITE"', 1, 1, 1, 3750000, 'https://bizweb.dktcdn.net/100/377/398/products/554724-075-29748ac3-4e0c-46cd-8faf-2cd3de7a16d8.png?v=1655382585127'),
	(N'AIR JORDAN 1 LOW "UNIVERSITY GOLD"', 1, 1, 6, 2910000, 'https://bizweb.dktcdn.net/100/377/398/products/553558-700.png?v=1655384943277'),
	(N'AIR JORDAN 1 LOW SIREN RED "BLACK/WHITE"', 1, 1, 1, 3250000, 'https://bizweb.dktcdn.net/100/377/398/products/dc0774-004.png?v=1655384200617'),
	(N'AIR JORDAN 1 MID SE "TURF ORANGE"', 1, 1, 7, 3150000, 'https://bizweb.dktcdn.net/100/377/398/products/bq6931-802.png?v=1655385012403'),
	(N'AIR JORDAN 1 MID "DESERT OCHRE/BLACK"', 1, 1, 6, 2970000, 'https://bizweb.dktcdn.net/100/377/398/products/db5453-700.png?v=1655378934097'),
	(N'AIR JORDAN 1 MID "GYM RED BLACK"', 1, 1, 9, 2910000, 'https://bizweb.dktcdn.net/100/377/398/products/bq6472-601.png?v=1655383755707'),
	(N'AIR JORDAN 1 LOW SE "MULTI COLOR" WHITE/HYPER ROYAL', 1, 1, 2, 2910000, 'https://bizweb.dktcdn.net/100/377/398/products/db5455-100.png?v=1655379102207'),
	(N'AIR JORDAN 3 RETRO VASITY ROYAL CEMENT BLUE/BLACK', 1, 1, 4, 4470000, 'https://bizweb.dktcdn.net/100/377/398/products/ct8532-400.png?v=1655378621963'),
	(N'AIR JORDAN 3 RETRO SE WHITE/ FIRE RED-BLACK', 1, 1, 2, 3270000, 'https://bizweb.dktcdn.net/100/377/398/products/ct8532-400.png?v=1655378621963'),
	(N'AIR JORDAN 3 RETRO "CARDINAL RED"', 1, 1, 2, 3270000, 'https://bizweb.dktcdn.net/100/377/398/products/398614-126.png?v=1655382985437'),
	(N'AIR JORDAN 4 RETRO GREY VOLT FIRST LOOK', 1, 1, 3, 2910000, 'https://bizweb.dktcdn.net/100/377/398/products/ct5343-007.png?v=1655384541657'),
	(N'AIR JORDAN 6 RETRO ALLIGATOR', 1, 1, 2, 2370000, 'https://bizweb.dktcdn.net/100/377/398/products/384665-110.png?v=1655384322797'),
	(N'NIKE BLAZER MID 77 VINTAGE "WHITE/BLACK"', 1, 1, 2, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-13t114840-946.png?v=1663044524547'),
	(N'NIKE BLAZER MID 77 NEXT NATURE "LIGHT OREWOOD BROWN"', 1, 1, 6, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/dq4124-100.png?v=1655797299147'),
	(N'NIKE BLAZER MID 77 MULTI SWOOSH "WHITE/KUMQUAT"', 1, 1, 2, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-13t113650-679.png?v=1663043815897'),
	(N'NIKE BLAZER LOW 77 "ALL WHITE"', 1, 1, 2, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/dc4769-101.png?v=1655796235967'),
	(N'NIKE BLAZER MID 77 "WHITE PENDANTS"', 1, 1, 2, 2310000, 'https://bizweb.dktcdn.net/100/377/398/products/dm0850-100.png?v=1655797045483'),
	(N'NIKE AIR FORCE 1 SE BABY DRAGON GOLD GREY', 1, 1, 6, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/ci3910-100.png?v=1655434750080'),
	(N'NIKE AIR FORCE 1 TYPE 2 TRIPLE WHITE', 1, 1, 2, 2650000, 'https://bizweb.dktcdn.net/100/377/398/products/ct2584-100.png?v=1655436291260'),
	(N'NIKE AIR FORCE 1 BLACK SKELETON', 1, 1, 1, 890000, 'https://bizweb.dktcdn.net/100/377/398/products/bq7541-001.png?v=1655389677777'),
	(N'NIKE AIR FORCE 1 "GREEN PAISLEY"', 1, 1, 5, 2730000, 'https://bizweb.dktcdn.net/100/377/398/products/dh4406-102.png?v=1655439262183'),
	(N'NIKE AIR FORCE 1 LOW "BLACK PAISLEY"', 1, 1, 1, 2730000, 'https://bizweb.dktcdn.net/100/377/398/products/dh4406-101.png?v=1655439432577'),
	(N'NIKE AIR FORCE 1 FONTANKA "WHITE/YELLOW"', 1, 1, 6, 1770000, 'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-13t114252-887.png?v=1663044176520'),
	(N'NIKE AIR FORCE 1 SKELETON QS', 1, 1, 2, 890000, 'https://bizweb.dktcdn.net/100/377/398/products/bq7541-100.png?v=1655389812447'),
	(N'NIKE AIR FORCE 1 "STARS"', 1, 1, 6, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/ci3910-100.png?v=1655434750080'),
	(N'NIKE AIR FORCE 1 SHADOW TRIPLE WHITE', 1, 1, 2, 2310000, 'https://bizweb.dktcdn.net/100/377/398/products/ci0919-100.png?v=1655433582737'),
	(N'NIKE AIR FORCE 1 LV8 3 WHEAT', 1, 1, 6, 1380000, 'https://bizweb.dktcdn.net/100/377/398/products/bq5485-700.png?v=1655389496543'),
	(N'NIKE AIR FORCE 1 LOW UNDERCONSTRUCTION', 1, 1, 1, 1470000, 'https://bizweb.dktcdn.net/100/377/398/products/bq5484-001.png?v=1655389001930'),
	(N'NIKE DUNK LOW "WHITE PAISLEY"', 1, 1, 2, 2370000, 'https://bizweb.dktcdn.net/100/377/398/products/dj9955-100.png?v=1657524024797'),
	(N'NIKE DUNK LOW 2021 "BLACK/WHITE"', 1, 1, 1, 3840000, 'https://bizweb.dktcdn.net/100/377/398/products/cw1590-100-1-39039349-f146-4687-b0b9-4cf09d178ce9.png?v=1655894639490'),
	(N'NIKE DUNK LOW "ORANGE PEARL"', 1, 1, 7, 3840000, 'https://bizweb.dktcdn.net/100/377/398/products/dd1503-102.png?v=1655894461833'),
	(N'NIKE AIR MAX 97 HIGHLIGHTED IN AURORA GREEN', 1, 1, 5, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/cz3574-130.png?v=1655525887347'),
	(N'NIKE AIR MAX 97 HABANERO WHITE RED', 1, 1, 9, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/cq4817-100.png?v=1655525315457'),
	(N'NIKE AIR MAX 97 "GLITTER"', 1, 1, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/at0071-002.png?v=1655459354697'),
	(N'NIKE AIR MAX 98 CUT AWAY WHITE', 1, 1, 2, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/cj0634-101.png?v=1655522729277'),
	(N'NIKE AIR MAX 97 WHITE', 1, 1, 2, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/921522-104.png?v=1655455151283'),
	(N'NIKE AIR MAX 97 "BARCODE" BROWN GOLD', 1, 1, 6, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/921826-201.png?v=1655456034490'),
	(N'NIKE AIR MAX 97 PE BEIGE', 1, 1, 6, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/bq7231-200.png?v=1655519915093'),
	(N'NIKE AIR MAX 97 BLACK MULTI STICH', 1, 1, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/ck0738-001.png?v=1655524768677'),
	(N'NIKE AIR MAX 97 x JAYSON TATUM', 1, 1, 9, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/cj9891-600.png?v=1655523063197'),
	(N'ADIDAS STAN SMITH HER STUDIO LONDON "CLOUD WHITE/POWER BERRY"', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/fw2524.png?v=1655785209390'),
	(N'ADIDAS STAN SMITH x FIORUCCI "CLOUD WHITE CORE BLACK"', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eg5152-3441a919-50e8-470e-8572-95ca30f2d233.png?v=1655783971603'),
	(N'ADIDAS STAN SMITH IRIDESCENT LINE', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eh0780-1.png?v=1655903374993'),
	(N'ADIDAS STAN SMITH PURPLE TINT', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/gz8142.png?v=1655784398990'),
	(N'ADIDAS STAN SMITH GOLD METALLIC', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eh2037.png?v=1655786603727'),
	(N'ADIDAS STAN SMITH PINK', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/b32703.png?v=1655780034710'),
	(N'ADIDAS STAN SMITH "DIAMOND"', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/ee8483-73f73a04-445e-49f6-afac-7edece1e6e7d.png?v=1655783114897'),
	(N'ADIDAS STAN SMITH RUBY SILVER', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eg7296-b45ebf6a-54dc-4c32-b6b4-64430e34086a.png?v=1655903295233'),
	(N'ADIDAS STAN SMITH GOLD', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eg9564-ccf1bae2-bc15-48d6-9214-69f9474b63ce.png?v=1655903344510'),
	(N'ADIDAS STAN SMITH TICTILE ROSE', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/fv6326-1.png?v=1655902547667'),
	(N'ADIDAS ULTRABOOST 19 x TOY STORY 4 "BUZZ LIGHTYEAR"', 1, 2, 5, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/ef0933-e2c36234-8619-4106-b444-3ef98f705aaa.png?v=1655804010427'),
	(N'ADIDAS ULTRABOOST 19 CORE BLACK GREY SIX SHOCK CYAN', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/f35242-3c27e5c8-fb4f-45bd-97ab-33f8115a6f8f.png?v=1655897389087'),
	(N'ADIDAS ULTRABOOST UNCAGED NAVY BLACK', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/b43519.png?v=1655800713660'),
	(N'ADIDAS ULTRABOOST 19 OREO', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/b75879.png?v=1655801469507'),
	(N'ADIDAS ULTRABOOST X ALL WHITE', 1, 2, 2, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/bb3433.png?v=1655802044473'),
	(N'ADIDAS ULTRABOOST 4.0 CORE BLACK', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/bb6149.png?v=1655802676983'),
	(N'ADIDAS ULTRABOOST 19 TRIPLE BLACK', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/ef1345-544dc117-111d-49b4-8fe2-75066e359fac.png?v=1655897305687'),
	(N'ADIDAS ULTRABOOST 3.0 CORE BLACK', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/s82103-0c9343d6-2991-4f58-b40d-d9bf5aeca837.png?v=1655897584500'),
	(N'ADIDAS ULTRABOOST 5.0 DNA "WHITE"', 1, 2, 9, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/gw7659.png?v=1655897088280'),
	(N'ADIDAS ULTRABOOST 4.0 ORCHID TINT BLACK', 1, 2, 2, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/db3197-2c16d4bf-32d3-4a88-996b-7ef6c093dabd.png?v=1655803986397'),
	(N'ADIDAS ULTRABOOST 1.0 OG LILAC BLACK', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/g28319-bae7ae7b-c1b6-40ef-a13e-0b930093967f.png?v=1655897432537'),
	(N'ADIDAS ULTRABOOST 20 "CORE BLACK/SILVER METALLIC"', 1, 2, 1, 1550000, 'https://bizweb.dktcdn.net/100/377/398/products/fv8333.png?v=1655799595953'),
	(N'ADIDAS HUMAN RACE PHARRELL "CRYSTAL WHITE"', 1, 2, 2, 2900000, 'https://bizweb.dktcdn.net/100/377/398/products/q46467-f6e6dc18-0d7f-4af0-ad6e-dc96120c6cd8.png?v=1655888049267'),
	(N'ADIDAS HUMAN RACE PHARRELL "CORE WHITE"', 1, 2, 2, 2900000, 'https://bizweb.dktcdn.net/100/377/398/products/gy0092-1.png?v=1655888966473'),
	(N'ADIDAS SUPERSTAR CLOUD WHITE/CORE BLACK', 1, 2, 1, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/eg4958.png?v=1655457235917'),
	(N'ADIDAS SUPERSTAR CNY WHITE RED 2019', 1, 2, 9, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/g27571.png?v=1655457688877'),
	(N'ADIDAS SUPERSTAR GLOSSY TOE WHITE SILVER', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/cq2702-bf9d7855-1355-4bf8-8672-7a6596070ff0.png?v=1655457191937'),
	(N'ADIDAS SUPERSTAR SIGNAL PINK/SOCK PURBLE', 1, 2, 8, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/fw2502.png?v=1655458091207'),
	(N'ADIDAS SUPERSTAR "VALENTINE DAY"', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/fv3289-b5f2c1c5-b2c4-4a69-b69c-31c75d87419d.png?v=1655457611640'),
	(N'ADIDAS SUPERSTAR 80S METAL TOE', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/cp9945.png?v=1655457165387'),
	(N'ADIDAS SUPERSTAR WHITE ROSE GOLD', 1, 2, 6, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/cg5463-bfaf13c5-d725-45e9-b9d6-0fc2d1bcaeba.png?v=1655457113590'),
	(N'ADIDAS SUPERSTAR ZEBRA HOLOGRAM', 1, 2, 2, 990000, 'https://bizweb.dktcdn.net/100/377/398/products/4b1e690c-794b-4dae-9ee3-491b6000f99b.jpg?v=1596957810737'),
	(N'ADIDAS NMD R2 BLACK RED', 1, 2, 1, 2900000, 'https://bizweb.dktcdn.net/100/377/398/products/ba7252-7517990a-ec04-48b0-92c0-0b13480ffdba.png?v=1655888478530'),
	(N'ADIDAS NMD R2 WHITE RED', 1, 2, 2, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/ba7253-9454e7ac-7499-4b4c-addc-1555ac1b95ad.png?v=1655888506870'),
	(N'ADIDAS NMD R1 "ICE PURPLE"', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/ba7751-313f7cb5-16cf-4501-ab8d-3e6f9448e835.png?v=1655888531997'),
	(N'ADIDAS NMD XR1 UTILITY BLACK', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/bb2370-f9ab2584-fa71-436f-950f-b1bf23084165.png?v=1655888565817'),
	(N'ADIDAS NMD R2 BLACK JAPAN', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/by9696-721fbbf1-c2bb-4952-93aa-8ee9eada1e95.png?v=1655888770397'),
	(N'ADIDAS NMD R2 BLACK ORANGE', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/cg3384.png?v=1655734664927'),
	(N'ADIDAS NMD R2 "WOLF GREY"', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/cq2400.png?v=1655734969203'),
	(N'ADIDAS NMD R2 "BLACK WHITE"', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/cq2402.png?v=1655735124533'),
	(N'ADIDAS NMD R1 DUCK CAMO', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/d96616.png?v=1655900918650'),
	(N'ADIDAS NMD R1 "METALLIC PLUGS"', 1, 2, 1, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/ee5172.png?v=1655901346520'),
	(N'ADIDAS NMD R1 PINK', 1, 2, 8, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/ee6682.png?v=1655904316127'),
	(N'ADIDAS NMD R2 RED GUM WHITE', 1, 2, 9, 1250000, 'https://bizweb.dktcdn.net/100/377/398/products/cq2404.png?v=1655900666030'),
	(N'ADIDAS EQT ADV SUPPORT VINTAGE BLACK', 1, 2, 1, 2650000, 'https://bizweb.dktcdn.net/100/377/398/products/by9587.png?v=1655729877040'),
	(N'ADIDAS EQT ADV SUPPORT PINK BLACK XENO', 1, 2, 9, 2650000, 'https://bizweb.dktcdn.net/100/377/398/products/bb0543.png?v=1655729573643'),
	(N'ADIDAS SUPERCOURT WHITE HOLOGRAM', 1, 2, 2, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/eg8489.png?v=1655793344073'),
	(N'ADIDAS SUPERCOURT WHITE SCARLET SUEDE', 1, 2, 2, 610000, 'https://bizweb.dktcdn.net/100/377/398/products/ef9181.png?v=1655792633313'),
	(N'ADIDAS ADILETTE SLIDES X THE SIMPSONS "BLUE/WHITE"', 2, 2, 4, 625000, N'https://bizweb.dktcdn.net/thumb/medium/100/377/398/products/www-tramtabo-vn-2022-10-20t111229-413.png?v=1666239155717'),
	(N'ADIDAS ADILETTE LITE SLIDES WHITE', 2, 2, 2, 400000, N'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-23t144753-580.png?v=1663919278990'),
	(N'ADIDAS ADILETTE SHOWER BLACK/WHITE', 2, 2, 1, 300000, N'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn-2022-09-13t122123-131.png?v=1663046494470'),
	(N'ADIDAS ADILETTE SHOWER SLIDES CLOUD WHITE/GOLD METALLIC/BOLD GOLD', 2, 2, 6, 400000, N'https://bizweb.dktcdn.net/100/377/398/products/gz5931.png?v=1656309703763'),
	(N'ADIDAS ADILETTE SHOWER SLIDES "VIVID RED/WONDER WHITE/VIVID RED"', 2, 2, 9, 400000, N'https://bizweb.dktcdn.net/100/377/398/products/gw8751.png?v=1655881843970'),
	(N'NIKE VICTORI ONE NEXT NATURE ARCTIC ORANGE "WHITE ONYX/SAIL"', 2, 1, 8, 490000, N'https://bizweb.dktcdn.net/100/377/398/products/www-tramtabo-vn.png?v=1681119718127'),
	(N'NIKE KAWA BLACK WHITE', 2, 1, 2, 475000, N'https://bizweb.dktcdn.net/thumb/medium/100/377/398/products/819352-100-1.png?v=1655885298427'),
	(N'NIKE KAWA RED GREY', 2, 1, 9, 260000, N'https://bizweb.dktcdn.net/thumb/medium/100/377/398/products/819352-600.png?v=1655885323657'),
	(N'NIKE KAWA ALL BLACK', 2, 1, 1, 475000, N'https://bizweb.dktcdn.net/100/377/398/products/819352-001.png?v=1655885179883'),
	(N'NIKE BENASSI WHITE METALLIC YELLOW', 2, 1, 6, 475000, N'https://bizweb.dktcdn.net/100/377/398/products/343881-110.png?v=1655882535657');
GO

UPDATE dbo.Product
SET brandId = 3
WHERE productName LIKE '%JORDAN%'

-- TABLE: [Size]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Size]
(
    [sizeId] INT PRIMARY KEY IDENTITY,
    [sizeEu] INT UNIQUE NOT NULL
);
GO

DECLARE @size INT = 40;
WHILE @size <= 50
BEGIN
    INSERT INTO [Size](sizeEu)
    VALUES (@size);
    SET @size = @size + 1;
END;

-- TABLE: [ProductSize]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [ProductSize]
(
    [productSizeId] INT PRIMARY KEY IDENTITY,
    [productId] INT NOT NULL REFERENCES [Product](productId),
    [sizeId] INT NOT NULL REFERENCES [Size](sizeId),
	UNIQUE(productId, sizeId)
);
GO

DECLARE @productId INT = 1;
DECLARE @totalProducts INT = (SELECT COUNT(*) FROM dbo.Product);
WHILE @productId <= @totalProducts
BEGIN
    DECLARE @sizeId INT = 1;
    WHILE @sizeId <= 5
    BEGIN
        INSERT INTO [productSize](productId, sizeId)
        VALUES (@productId, @sizeId);
        SET @sizeId = @sizeId + 1;
    END;
    SET @productId = @productId + 1;
END;

-- TABLE: [Account]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Account]
(
	[accountId] INT PRIMARY KEY IDENTITY,
	[fullname] NVARCHAR(255) NOT NULL,
	[email] NVARCHAR(255) UNIQUE NOT NULL,
	[password] NVARCHAR(255) NOT NULL,
	[isAdmin] BIT NOT NULL DEFAULT 0
);
GO 

INSERT INTO [Account]
	([fullname], [email], [password], [isAdmin])
VALUES
	(N'Admin', N'admin@email.com', N'admin', 1),
	(N'User', N'user@email.com', N'user', 0);
GO

-- TABLE: [Order]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [Order]
(
    [orderId] INT PRIMARY KEY IDENTITY,
	[consignee] NVARCHAR(255),
	[phone] NVARCHAR(255),
	[address] NVARCHAR(MAX),
	[total] MONEY,
	[status] NVARCHAR(255) DEFAULT 'Pending',
	[accountId] INT REFERENCES [Account](accountId)
);
GO

-- TABLE: [OrderDetail]
-----------------------------------------------------------------------------------------------------------------------
CREATE TABLE [OrderDetail]
(
    [orderDetailId] INT PRIMARY KEY IDENTITY,
    [orderId] INT REFERENCES [Order](orderId),
    [productSizeId] INT REFERENCES [ProductSize](productSizeId),
    [quantity] INT,
	[total] MONEY
);
GO
