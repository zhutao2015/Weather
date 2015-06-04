import sys, os
ROOT = os.path.abspath(os.path.dirname(__file__))
def has_dl(x):
    name = x + ".png"
    return os.path.exists(os.path.join(ROOT, name))
urls = []
for i in [1,2,3,4,9,10,11,13]:
    for time in ["d", "n"]:
        x = "{}{}".format(str(i).rjust(2, "0"), time)
        if has_dl(x):
            print "{} has already been downloaded! skip!".format(x)
        else:
            url = "http://openweathermap.org/img/w/{}.png".format(x)
            urls.append(url)

for url in urls:
    print "downloading {}...".format(url)
    os.system("wget {}".format(url))
