### fp.seek(offset, from_what)

where fp is the file pointer you're working with;
* offset means how many positions you will move
* from_what defines your point of reference:
  * 0: means your reference point is the beginning of the file
  * 1: means your reference point is the current file position
  * 2: means your reference point is the end of the file
  * if omitted, from_what defaults to 0.