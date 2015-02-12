--新增procedure loop99
create procedure loop99 (@x int, @y int)
as
begin
declare @i int,
		@j int,
		@s varchar(300)
	set @i = 1
	while (@i <= @x)
	begin
		set @j = 1
		set @s = ''
		while (@j <= @y)
		begin
			set @s = @s + '   ' + RIGHT(' ' + cast( @i as varchar(2)), 2) 
						+ ' *' + RIGHT(' ' + cast( @j as varchar(2)), 2)
						+ ' =' + RIGHT(' ' + cast( @i * @j as varchar(2)), 2)
			set @j = @j + 1
		end
		print @s
		set @i = @i + 1
	end
end;

--執行procedure loop99
exec loop99 9, 9

--刪除procedure loop99
drop procedure loop99