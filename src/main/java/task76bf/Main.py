mp = {}
# user_code
def get(key):
    res = mp['res']
    index = str(res).find('{'+key+':')
    if index < 0 :
        return 0
    else:
        return res[index + 1 +len(key)+1:res.find('}',res.find(key))]


def put(key):
    offset = 0
    b = [0 for i in range(203)]
    b.append('\n')

    if get(key) == 0:
        for line in mp['res'].split('\n'):
            offset += 204

        s = '{%s:%d}' % (key , 1)
        newIn = bytearray(s)
        for i in xrange(len(newIn)):
            b[i] = newIn[i]

        b_s = bytearray(b)
        a = ''.join(map(chr, b_s))
        mp['res'] += a
        #write_file(offset, b_s)
    else:
        print mp['res'].split('\n')[:-1]
        for line in mp['res'].split('\n')[:-1]:
            if line.find('{'+key) < 0 :
                offset += 204
            else:
                offset += len('{'+key) + 1 + len(':')
                break
        b_s = bytearray(str(int(get(key))+1))
        new = bytearray(mp['res'])

        for index,i in enumerate(b_s):
            new[new.find('{'+key) + 1 + len(key)+1+index] = chr(i)

        mp['res'] = ''.join(map(chr, new))

        #write_file(offset, b_s)


def init():
    ls = map(chr, read_file(0, 102400))
    mp['res'] = ''.join(ls)


offset = 0
b = [0 for i in range(203)]
b.append('\n')


s = '{%s:%d}' % ('aa', 15)
newIn = bytearray(s)

for i in xrange(len(newIn)):
    b[i] = newIn[i]
a_s = bytearray(b)

b = [0 for i in range(203)]
b.append('\n')
s = '{%s:%d}' % ('112', 13)
newIn = bytearray(s)

for i in xrange(len(newIn)):
    b[i] = newIn[i]

b_s = bytearray(b)+a_s
# print b_s
# mp['res'] = ''.join(b_s)

#print mp
# print len(b_s)
# print [x for x in b_s]
ls = map(chr, b_s)
mp['res'] = ''.join(ls)

#print mp
#print get('112')
#
# print s[s.find('aa')+len('aa')+1:s.find('}',s.find('aa'))]
put('c134')
put('c134')
put('c134')
print get('c134')
print get('34')
put('c34')
print get('c34')
put('cc')
print get('cc')
print get('d')
print get('c')