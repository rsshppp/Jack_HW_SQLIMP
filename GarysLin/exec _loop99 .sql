 create procedure loop99 (@xx int, @yy int)
    AS
    begin
		DECLARE @x int
		DECLARE @y int

		set @x=1
		set @y=1

		while @x<=@xx
		begin
		set @y=1
			while @y<=@yy
			begin
				print CAST(@x as NVARCHAR)+'x'+CAST(@y as NVARCHAR)+' = '+CAST((@x*@y)as NVARCHAR)
				set @y+=1
			end;
			set @x+=1
		end;

    end;

	exec  loop99  9,9 ; 

