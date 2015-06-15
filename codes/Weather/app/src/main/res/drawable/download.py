import sys, os
ROOT = os.path.abspath(os.path.dirname(__file__))
def has_dl(x):
    name = x + ".png"
    return os.path.exists(os.path.join(ROOT, name))

def dl():
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

def rename():
    for f in os.listdir(ROOT):
        if f[0].isdigit() and f.endswith(".png"):
            os.rename(os.path.join(ROOT, f), os.path.join(ROOT, "w"+f))

if __name__ == "__main__":
    choice = raw_input("Download(D) or rename(R)?")
    if choice == "D":
        dl()
    elif choice == "R":
        rename()
    else:
        print "You need to input D for Download or R for Rename!"

