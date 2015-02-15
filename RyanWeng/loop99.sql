create procedure loop99
       @m int,   -- 要加,
	   @n int  
as
declare @i int=1, -- 要加,
        @j int=1
while(@i<=@m)
begin
    while(@j<=@n)
    begin
	    print convert(char(2),@i)+'*'+ convert(char(2),@j)+'='+convert(char(2),@i*@j)
	    set @j=@j+1
    end
	set @j=1   --this step is important to reset @j
	set @i=@i+1
end


