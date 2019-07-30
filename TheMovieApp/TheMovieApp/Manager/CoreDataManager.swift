//
//  CoreDataManager.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 27/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import Foundation
import CoreData
import UIKit

class CoreDataManager {
    
    let appDelegate:AppDelegate
    let context:NSManagedObjectContext
    
    init() {
        appDelegate = UIApplication.shared.delegate as! AppDelegate
        context = appDelegate.persistentContainer.viewContext
    }
    
    func saveObject(_ object:NSManagedObject, successCompletion: @escaping() -> Void, failCompletion: @escaping(_ error: Error) -> Void) {
        do {
            try context.save()
            successCompletion()
            return
        } catch {
            context.delete(object)
            failCompletion(error)
        }
    }
    
    func getFavoriteMovies() -> [MovieData]? {
        let fetchRequest = NSFetchRequest<MovieData>(entityName: "MovieData")
        do {
            let list = try context.fetch(fetchRequest)
            return list
        } catch {
            print("error 321")
        }
        return nil
    }
    
    func fetch<T: NSManagedObject>(_ entity: T.Type,
                                     predicate: NSPredicate? = nil,
                                     successCompletion: @escaping(_ fetchedArray:[T]) -> Void,
                                     failCompletion: @escaping(_ error: Error) -> Void) {
        let fetchRequest = NSFetchRequest<T>(entityName: NSStringFromClass(T.self))
        
        if predicate != nil {
            fetchRequest.predicate = predicate!
        }
        fetchRequest.returnsObjectsAsFaults = false
        do {
            let searchResult = try context.fetch(fetchRequest)
            if searchResult.count > 0 {
                successCompletion(searchResult)
                return
            } else {
                successCompletion([])
            }
        } catch {
            failCompletion(error)
        }
    }
    
    func delete<T: NSManagedObject>(_ entity: T.Type,
                                    predicate: NSPredicate,
                                    successCompletion: @escaping() -> Void,
                                    failCompletion: @escaping(_ error: Error) -> Void) {
        let fetchRequest = NSFetchRequest<T>(entityName: NSStringFromClass(T.self))
        fetchRequest.predicate = predicate
        fetchRequest.returnsObjectsAsFaults = false
        do {
            let searchResult = try context.fetch(fetchRequest)
            if (searchResult.count > 0) {
                context.delete(searchResult[0])
                do {
                    try context.save()
                    successCompletion()
                } catch {
                    failCompletion(error)
                }
            } else {
                failCompletion(NSError(domain: Bundle.main.bundleIdentifier ?? "com.appCompany.AppName" , code:-111, userInfo:[ NSLocalizedDescriptionKey: "No items to delete"]))
            }
        } catch {
            failCompletion(error)
        }
    }
}
