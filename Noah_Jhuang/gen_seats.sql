
--程式開始
BEGIN
  DECLARE   @v_row     int;
  DECLARE   @v_col     int;
  DECLARE   @v_ptime   varchar(20); 
  DECLARE   @v_movie   int;
  DECLARE   @v_roomid  varchar(10);
  DECLARE   @x_row     int;
  DECLARE   @x_col     int;

  --設定變數值
  SET @v_ptime  = '2009-12-25 13:00';
  SET @v_movie  = 1;
  SET @v_roomid = 'A廳';

  --先查詢 指定廳院之座位數 row, col
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
									 RIGHT('0' + cast( @x_row as varchar(2)), 2)
									 + '-' + RIGHT('0' + cast( @x_col as varchar(2)), 2), '0', NULL);
            SET @x_col = @x_col + 1		--增加 @x_row
          END; 
         SET @x_row = @x_row + 1	--增加 @x_col
      END;
END;
    
--程式結束
--測試輸出指令
select * from seats where seat_num = '01-25'

select CONVERT(varchar(16), ptime ,121) as ptime, movie, seat_num, sold, custid from seats