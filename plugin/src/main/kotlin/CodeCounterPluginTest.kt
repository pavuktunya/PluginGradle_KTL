/*
class CodeCounterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.task("countCodeStats") {
            doLast {
                val sourceSets = project.convention.getPlugin(KotlinPluginConvention::class.java).sourceSets
                val mainSourceSet = sourceSets["main"]

                val sourceDirectories = mainSourceSet.allSource.srcDirs
                val codeLineCount = sourceDirectories.sumBy { countLines(it) }

                val javaClassCount = countClasses(mainSourceSet.java.srcDirs, JavaLexer::class.java, JavaParser::class.java)
                val kotlinClassCount = countClasses(mainSourceSet.kotlin.srcDirs, KotlinClassFinder::class.java, KotlinParser::class.java)

                val javaMethodCount = countMethods(mainSourceSet.java.srcDirs, JavaLexer::class.java, JavaParser::class.java)
                val kotlinMethodCount = countMethods(mainSourceSet.kotlin.srcDirs, KotlinLexer::class.java, KotlinParser::class.java)

                val result = mapOf(
                    "codeLineCount" to codeLineCount,
                    "javaClassCount" to javaClassCount,
                    "kotlinClassCount" to kotlinClassCount,
                    "javaMethodCount" to javaMethodCount,
                    "kotlinMethodCount" to kotlinMethodCount
                )
                println(result)
            }
        }
    }

    private fun countLines(directory: Any): Int {
        return try {
            directory.list()?.sumBy { file ->
                if (file.isDirectory) {
                    countLines(file)
                } else {
                    file.readLines().size
                }
            } ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun countClasses(directories: Set<File>, lexerClass: Class<out Lexer>, parserClass: Class<out Parser>): Int {
        return countItemsInFiles(directories, lexerClass, parserClass) { parser, tree ->
            countClassDeclarations(parser, tree)
        }
    }

    private fun countMethods(directories: Set<File>, lexerClass: Class<out Lexer>, parserClass: Class<out Parser>): Int {
        return countItemsInFiles(directories, lexerClass, parserClass) { parser, tree ->
            countMethodDeclarations(parser, tree)
        }
    }

    private fun <T : Parser> countItemsInFiles(
        directories: Set<File>,
        lexerClass: Class<out Lexer>,
        parserClass: Class<T>,
        countFunction: (T, ParseTree) -> Int
    ): Int {
        val parser = createParser(parserClass)

        return directories.sumBy { directory ->
            try {
                directory.listFiles { file ->
                    file.extension == "java" || file.extension == "kt"
                }?.sumBy { file ->
                    parseFile(file, lexerClass, parser, countFunction)
                } ?: 0
            } catch (e: Exception) {
                0
            }
        }
    }

    private fun <T : Parser> createParser(parserClass: Class<T>): T {
        return try {
            parserClass.getDeclaredConstructor(TokenStream::class.java).newInstance(null)
        } catch (e: Exception) {
            throw IllegalArgumentException("Failed to create parser instance", e)
        }
    }

    private fun parseFile(
        file: File,
        lexerClass: Class<out Lexer>,
        parser: Parser,
        countFunction: (Parser, ParseTree) -> Int
    ): Int {
        val lexer = createLexer(lexerClass, file)
        val tokens = CommonTokenStream(lexer)
        parser.inputStream = tokens

        val tree: ParseTree = when (parser) {
            is JavaParser -> parser.compilationUnit()
            is KotlinParser -> parser.kotlinFile()
            else -> throw IllegalArgumentException("Unsupported parser type")
        }

        return countFunction(parser, tree)
    }

    private fun createLexer(lexerClass: Class<out Lexer>, file: File): Lexer {
        return try {
            val inputStream: CharStream = CharStreams.fromFileName(file.path)
            lexerClass.getDeclaredConstructor(CharStream::class.java).newInstance(inputStream)
        } catch (e: Exception) {
            throw IllegalArgumentException("Failed to create lexer instance", e)
        }
    }

    private fun countClassDeclarations(parser: Parser, tree: ParseTree): Int {
        return countDeclarations(parser, tree, JavaParser::class.java, "classDeclaration")
    }

    private fun countMethodDeclarations(parser: Parser, tree: ParseTree): Int {
        return countDeclarations(parser, tree, JavaParser::class.java, "methodDeclaration") +
                countDeclarations(parser, tree, KotlinParser::class.java, "functionDeclaration")
    }

    private fun countDeclarations(parser: Parser, tree: ParseTree, parserClass: Class<*>, declarationRule: String): Int {
        val visitor = DeclarationCounterVisitor(parserClass, declarationRule)
        ParseTreeWalker.DEFAULT.walk(visitor, tree)
        return visitor.declarationCount
    }

    private class DeclarationCounterVisitor(
        private val parserClass: Class<*>,
        private val declarationRule: String
    ) : ParseTreeVisitor<Unit> {

        var declarationCount = 0

        override fun visit(tree: ParseTree): Unit {
            val parserRuleContext = tree as? ParserRuleContext
            if (parserRuleContext != null && parserClass.isInstance(parserRuleContext.parser)) {
                if (parserRuleContext.ruleContext.javaClass.simpleName == declarationRule) {
                    declarationCount++
                }
            }

            for (i in 0 until tree.childCount) {
                visit(tree.getChild(i))
            }
        }
    }
}
*/
