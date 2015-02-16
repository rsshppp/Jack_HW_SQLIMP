create TRIGGER trig_seats 
ON    playlist
AFTER INSERT  --for�]�i
AS
BEGIN
  DECLARE   @v_row     int;
  DECLARE   @v_col     int;
  DECLARE   @x_row     int;
  DECLARE   @x_col     int;
  DECLARE   @v_ptime   varchar(20); 
  DECLARE   @v_movie   int;
  DECLARE   @v_roomid  varchar(10);

  --���d�M ���w�U�|���y��� row, col
  --�ޥηs���ȱq inserted ��� ; �L�k�����H inserted.ptime�s��
   
  SELECT @v_ptime = ptime, @v_movie = movie, @v_roomid =roomid
  FROM   inserted;
      
  select @v_row = seat_row , @v_col = seat_col
  from   m_room
  where  roomid=@v_roomid

  --�ھڮy��� @v_row, @v_col ���ͮy���
  SET @x_row = 1;     

  WHILE ( @x_row  <= @v_row )
    BEGIN
      SET @x_col = 1;     
      WHILE ( @x_col  <= @v_col )
        BEGIN
          insert into seats values ( @v_ptime, @v_movie, right('0'+convert(varchar(2),@x_row),2)+'-'+right('0'+convert(varchar(2),@x_col),2), '0', NULL);
          set @x_col+=1   --�W�[ @x_col
        END;
      set @x_row+=1 -- �]�w @x_row, @x_col
    END;
END;