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

  --先查尋 指定廳院之座位數 row, col
  select @v_row = seat_row , @v_col = seat_col  --設值語法
  from   m_room
  where  roomid = @v_roomid;

 -- select @v_col--for check only
  --根據座位數 @v_row, @v_col 產生座位表
  SET @x_row = 1;       
    WHILE ( @x_row  <= @v_row )
      BEGIN
        SET @x_col = 1;     
        WHILE ( @x_col  <= @v_col )
          BEGIN
            insert into seats values ( @v_ptime, @v_movie, right('0'+convert(varchar(2),@x_row),2)+'-'+right('0'+convert(varchar(2),@x_col),2), '0', NULL);
            set @x_col+=1   --增加 @x_col
          END; 
        set @x_row+=1 -- 設定 @x_row, @x_col
      END;
END;
    
--程式結束