import pdftotext
import pdf2txt


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Heil, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('Frappe l\'horloge !')


# Load your PDF
with open("../Corpus_2022/Boudin-Torres-2006.pdf", "rb") as f:
    pdf = pdftotext.PDF(f)

# How many pages?
print(len(pdf))

# Iterate over all the pages
for page in pdf:
    print(page)

# Read some individual pages
print(pdf[0])
print(pdf[1])

# Read all the text into one string
print("\n\n".join(pdf))