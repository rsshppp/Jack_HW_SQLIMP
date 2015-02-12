--新增procedure gen_seats
create procedure gen_seats (@v_ptime varchar(20),
							@v_movie int,
							@v_roomid varchar(10))
as
BEGIN
	DECLARE   @v_row     int;
	DECLARE   @v_col     int;
	DECLARE   @x_row     int;
	DECLARE   @x_col     int;

	--先查詢  指定廳院之座位數 row, col
	select @v_row = seat_row , @v_col = seat_col
	from   m_room
	where  @v_roomid = roomid

	--根據座位數 @v_row, @v_col 產生座位表
	SET @x_row = 1;       
    WHILE ( @x_row  <= @v_row )
    BEGIN
		SET @x_col = 1;     
		WHILE ( @x_col  <= @v_col )
        BEGIN
			insert into seats values ( @v_ptime, @v_movie,
									   RIGHT('0' + cast( @x_row as varchar(2)), 2) + '-' + 
									   RIGHT('0' + cast( @x_col as varchar(2)), 2), '0', NULL);
			SET @x_col = @x_col + 1		--增加 @x_col
        END; 
        SET @x_row = @x_row + 1		--增加 @x_row
    END;
END;

--測試輸出指令
--執行procedure gen_seats
exec gen_seats '2009-12-25 13:00', 1, 'A廳'

select * from seats where seat_num = '25-20'

--刪除procedure gen_seats
drop procedure gen_seats