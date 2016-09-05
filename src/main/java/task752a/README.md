http://www.qlcoder.com/task/752a

* awk '{print $2}' uv.txt | sort | uniq | wc -l

网友给出更简洁的版本
* awk '!a[$2]++' uv.txt | wc -l
