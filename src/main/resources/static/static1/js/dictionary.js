

class Phonetic{
    text=""
    audio=""

    constructor(text, audio) {
        this.text = text;
        this.audio = audio;
    }

    getText(){
        return this.text;
    }
    getAudio(){
        return this.audio;
    }

    setText(newText){
        this.text = newText;
    }
    setAudio(newAudio){
        this.audio= newAudio;
    }
}

class Definitions{
    definition=""
    example=[]
    constructor(definition, example){
        this.definition=definition;
        example.forEach(e=>{
            this.example.push(e);
        })
    }
    getDefinition(){
        return this.definition;
    }
    getExample(){
        return this.example;
    }
    setDefinition(definition){
        this.definition=definition;
    }
    setExample(example){
        this.example = example;
    }
}

class Meaning{
    partOfSpeech= ""
    definitions = []

    constructor(partOfSpeech, definitions){
        this.partOfSpeech=partOfSpeech;
        definitions.forEach(e=>{
            this.definitions.push(e);
        })
    }
    getDefinitions(){
        return this.definitions;
    }
    getPartOfSpeech(){
        return this.partOfSpeech;
    }
    setDefinition(definitions){
        this.definitions=definitions;
    }
    setExample(partOfSpeech){
        this.partOfSpeech = partOfSpeech;
    }
}

class Word {
    word = ""
    phonetics = []
    meaning = []
    synonyms=[]
    antonyms=[]

    constructor(word, phonetics, meaning, synonyms, antonyms){
        this.word = word;
        phonetics.forEach(e=>{
            this.phonetics.push(e);
        })
        meaning.forEach(e=>{
            this.meaning.push(e);
        })
        synonyms.forEach(e=>{
            this.synonyms.push(e);
        })
        antonyms.forEach(e=>{
            this.antonyms.push(e);
        })
    }

    getWord(){
        return this.word;
    }
    getPhonetics(){
        return this.phonetics;
    }
    getMeaning(){
        return this.meaning;
    }
    getSynonyms(){
        return this.synonyms;
    }
    getAntonym(){
        return this.antonyms;
    }

}

const defaultWordObject = {
    word: "beautiful",
    phonetics: [
        {
            text: "/ˈbjuːtɪfəl/",
            audio: "https://api.dictionaryapi.dev/media/pronunciations/en/beautiful-uk.mp3"
        },
        {
            "text":"/ˈbjuːtɪfəl/",
            "audio":"https://api.dictionaryapi.dev/media/pronunciations/en/beautiful-us.mp3"
        }
    ],
    meaning:[
        {
            partOfSpeech:"noun",
            definitions: [
                {
                    definition: "Someone who is beautiful. Can be used as a term of address.",
                    example: [
                        "Hey, beautiful!"
                    ]
                }

            ],

        },
        {
            partOfSpeech:"adjective",
            definitions: [
                {
                    "definition":"Attractive and possessing beauty.",
                    "example":["Anyone who has ever met her thought she was absolutely beautiful."]
                },
                {
                    "definition":"(of the weather) Pleasant; clear.",
                    "example":["It's beautiful outside, let's go for a walk."]
                },
                {
                    "definition":"Well executed.",
                    "example":["The skater performed a beautiful axel."]
                }

            ],
        }
    ],
    synonyms:[
        "bloody",
        "damned",
        "great",
        "intensifier",
        "just",
        "marvellous",
        "marvelous",
        "nice",
        "wonderful",
        "clear",
        "fine",
        "nice",
        "pleasant",
        "sunny",
        "attractive",
        "beauteous",
        "cute",
        "fair",
        "fit",
        "good-looking",
        "gorgeous",
        "handsome",
        "hot",
        "lovely",
        "nice-looking",
        "pretty",
        "shapely",
        "sheen",
        "excellent",
        "exceptional",
        "good",
        "great",
        "marvellous",
        "marvelous",
        "perfect",
        "stylish",
        "wonderful"
    ],
    antonyms: [
        "bad",
        "cloudy",
        "dull",
        "miserable",
        "overcast",
        "rainy",
        "wet",
        "grotesque",
        "hideous",
        "homely",
        "misshapen",
        "plain",
        "repulsive",
        "ugly",
        "unbeautiful",
        "average",
        "bad",
        "mediocre",
        "poor",
        "shoddy",
        "substandard",
        "terrible",
        "weak"
    ]
}

function removeChild(Element){
    while(Element.firstChild!=null){
        Element.removeChild(Element.firstChild);
    }
}


function upDateUI(theWord){
    var wordName = document.getElementById("wordName");
    wordName.innerText = theWord.word;

    var phoneticDiv = document.getElementById("phonetics");
    removeChild(phoneticDiv);
    theWord.phonetics.forEach(element =>{
        if(element.text != undefined){
            var phoneticBox = document.createElement("div");
            phoneticBox.setAttribute("class", "phonetic-box");
            var phoneticText = document.createElement("p");
            phoneticText.setAttribute("class", "wordPhonetic");
            phoneticText.setAttribute("id","wordPhonetic");
            phoneticText.innerHTML = element.text;
            var phoneticAudio = document.createElement("img");
            phoneticAudio.setAttribute("class", "wordAudio");
            phoneticAudio.setAttribute("src", "../public/volume-solid.png");
            phoneticAudio.setAttribute("alt", "");
            phoneticAudio.addEventListener('click', function(){
                new Audio(element.audio).play();
            } )
            phoneticBox.appendChild(phoneticText);
            phoneticBox.appendChild(phoneticAudio);
            phoneticDiv.appendChild(phoneticBox);
        }

    })

    var meaningPara = document.getElementById("meaningPara");
    removeChild(meaningPara)
    theWord.meaning.forEach(meaningElement=>{
        var meaningBlock = document.createElement("div")
        meaningBlock.setAttribute("class", "meaning-block");

        //parOfSpeech
        var partOfSpeechDiv = document.createElement("div");
        partOfSpeechDiv.setAttribute("class", "partOfSpeech");
        var partOfSpeech = document.createElement("h3");
        partOfSpeech.setAttribute("class", "partOfSpeechH3")
        partOfSpeech.innerHTML = meaningElement.partOfSpeech;
        partOfSpeechDiv.appendChild(partOfSpeech);

        meaningBlock.appendChild(partOfSpeechDiv);

        //Meaning Set
        var meaningSet = document.createElement("div");
        meaningSet.setAttribute("class", "meaning-set");

        meaningElement.definitions.forEach(definitionElement=>{
            var meaningGroup = document.createElement("div");
            meaningGroup.setAttribute("class", "meaning-group");

            //meaningbox
            var meaningbox = document.createElement("div");
            meaningbox.setAttribute("class", "meaning-box");
            var definition = document.createElement("p");
            definition.setAttribute("class", "definition");
            definition.innerHTML = definitionElement.definition;
            meaningbox.appendChild(definition);

            var exampleDiv = document.createElement("div");
            exampleDiv.setAttribute("class", "example");
            definitionElement.example.forEach(exampleElement=>{
                var example = document.createElement("p");
                example.innerHTML = exampleElement;
                exampleDiv.appendChild(example);
            })


            meaningGroup.appendChild(meaningbox);

            meaningGroup.appendChild(exampleDiv);

            meaningSet.appendChild(meaningGroup);
        })

        meaningBlock.appendChild(meaningSet);

        meaningPara.appendChild(meaningBlock)
    })

    var synonym_datas = document.getElementById("synonym-datas");
    removeChild(synonym_datas);
    var text1 = "";
    theWord.synonyms.forEach(element=>{
        text1 = text1 + element+", ";
    })
    var synonym_data = document.createElement("p");

    synonym_data.setAttribute("class", "synonym-data");
    synonym_data.innerHTML = text1;
    synonym_datas.appendChild(synonym_data);

    var antonym_datas = document.getElementById("antonym-datas");
    removeChild(antonym_datas);
    var text2 = "";
    theWord.antonyms.forEach(element=>{
        text2 = text2 + element+", ";
    })
    var antonym_data = document.createElement("p");
    antonym_data.setAttribute("class", "antonym-data");
    antonym_data.innerHTML = text2;
    antonym_datas.appendChild(antonym_data);

}

upDateUI(defaultWordObject);

function lookUp(){
    let value = document.querySelector('input').value;
    let val = value.toLowerCase();
    fetch('https://api.dictionaryapi.dev/api/v2/entries/en/'+val)
        .then(result => result.json())
        .then((output)=>{
            var result = output[0];

            var phonetic = [];
            result.phonetics.forEach(e=>{
                phonetic.push(new Phonetic(e.text, e.audio));
            })

            var symnonyms = []
            var antonyms = []
            var meaning = []
            result.meanings.forEach(meaningElement=>{
                var definitions = [];
                var parOfSpeech = meaningElement.partOfSpeech;
                meaningElement.definitions.forEach(definition=>{
                    var example = [];
                    if(definition.example != undefined){
                        example.push(definition.example);
                    }
                    var defi = new Definitions(definition.definition, example);
                    definitions.push(defi);
                })
                var meaningObject = new Meaning(parOfSpeech, definitions);
                meaning.push(meaningObject);

                meaningElement.synonyms.forEach(sy=>{
                    symnonyms.push(sy);
                })
                meaningElement.antonyms.forEach(anto=>{
                    antonyms.push(anto);
                })

            })

            var theWord = new Word(result.word, phonetic, meaning, symnonyms, antonyms);

            upDateUI(theWord);

        })
}