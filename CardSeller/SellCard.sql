	Create database SellCard
GO
USE SellCard
GO
CREATE TABLE [dbo].[User](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[username] [nvarchar](30) NULL,
	[password] [nvarchar] (max) NULL,
	[email] [nvarchar] (max) NULL,
	[phoneNumber] [nvarchar] (30) NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
	[role] [nvarchar](30)NULL,
) 

CREATE TABLE [dbo].[UserWallet](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserID] [int] NOT NULL UNIQUE references [User](ID),
	[amount] [float] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)

CREATE TABLE [dbo].[TransactionHistory](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserWalletID] [int] NOT NULL references [UserWallet](ID),
	[amount] [float] NULL,
	[method] [nvarchar](30) NULL,
	[processStatus] [bit] NULL,
	[successStatus] [bit] NULL,
	[createdAt] [date] NOT NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)


CREATE TABLE [dbo].[ProviderDetail](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[providerName] [nvarchar](30) NULL,
	[image][nvarchar](max) NULL,
	[category][nvarchar](30) NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)
CREATE TABLE [dbo].[CardDetail](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[ProviderID] [int] NOT NULL references [ProviderDetail](ID),
	[price] [float] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
	[quantity] [int] NULL,
)

CREATE TABLE [dbo].[CardDiscount](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[CardDetailID] [int] NOT NULL references [CardDetail](ID),
	[percent] [float] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[expiredAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)

CREATE TABLE [dbo].[Card](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[CardDetailID] [int] NOT NULL references [CardDetail](ID),
	[seriNumber] [int] NULL,
	[pinNumber] [int] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isBought] [bit] NULL,
	[boughtBy] [int] NULL,
	[boughtAt] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
) 

CREATE TABLE [dbo].[CartItem](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserID] [int] NOT NULL references [User](ID),
	[CardDetailID] [int] NOT NULL references [CardDetail](ID),
	[quantity] [int] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)

CREATE TABLE [dbo].[PaymentDetail](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserID] [int] NOT NULL references [User](ID),
	[amount] [float] NULL,
	[status] [nvarchar] (max) NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)

CREATE TABLE [dbo].[Order](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserID] [int] NOT NULL references [User](ID),
	[PaymentID] [int] NOT NULL UNIQUE references [PaymentDetail](ID),
	[total] [float] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)

CREATE TABLE [dbo].[OrderItem](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[OrderID] [int] NOT NULL references [Order](ID),
	[CardDetailID] [int] NOT NULL references [CardDetail](ID),
	[quantity] [int] NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)
CREATE TABLE [dbo].[Role](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[name] [nvarchar](30) NULL,
	[description] [nvarchar](max) NULL,
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)
CREATE TABLE [dbo].[UserRole](
	[ID] [int] IDENTITY(1,1) NOT NULL primary key,
	[UserID] [int] NOT NULL references [User](ID),
	[RoleID] [int] NOT NULL references [Role](ID),
	[createdAt] [date] NOT NULL,
	[updatedAt] [date] NULL,
	[createdBy] [int] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [int] NULL,
	[deletedAt] [date] NULL,
)
DROP TABLE [dbo].[Role]
DROP TABLE [dbo].[UserRole]
INSERT INTO [dbo].[CardDetail] (provider,category,createdAt) values ('Viettel','phonecard',getdate())
INSERT INTO [dbo].[CardDetail] (provider,createdAt) values ('Vinaphone',getdate())
INSERT INTO [dbo].[PriceDetail] (PhoneCardID,createdAt,price,discountPercent) values (1,getdate(),'10000',2)
INSERT INTO [dbo].[PriceDetail] (PhoneCardID,createdAt,price,discountPercent) values (2,getdate(),'20000',1)
SELECT COUNT(*) FROM ProviderDetail