package de.wvvh.stayhomeapp.quests

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object QuestManager {
    private val allQuests: MutableList<IQuestBuilder> = mutableListOf()
    private val _activeQuests: MutableList<IQuest> = mutableListOf()
    val activeQuests: List<IQuest>
        get() = _activeQuests

    fun loadModule(module: IQuestModule) = module.quests.forEach { register(it)}
    fun register(element: IQuestBuilder) = allQuests.add(element)
}